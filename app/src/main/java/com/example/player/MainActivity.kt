package com.example.player

import android.app.Activity
import android.content.ContentResolver
import android.content.pm.PackageManager
import android.database.Cursor
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.widget.TextView
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
import androidx.core.app.ActivityCompat
import androidx.core.app.ActivityCompat.requestPermissions
import androidx.core.content.ContentResolverCompat.query
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import java.security.Permission

var con = 0
lateinit var testTV: TextView

lateinit var list: MutableList<Song>
lateinit var layoutManager: RecyclerView.LayoutManager
lateinit var adapter: SongAdapter
lateinit var rvSong : RecyclerView
class MainActivity : AppCompatActivity() {
    @RequiresApi(Build.VERSION_CODES.R)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        rvSong = findViewById(R.id.rvSongs)
        testTV = findViewById(R.id.tvStartTime)
        list = mutableListOf()
        askPremision()
    }

    fun getSong(){
        val selection = MediaStore.Audio.Media.IS_MUSIC
        val projection = arrayOf(
            MediaStore.Audio.Media.ARTIST,
            MediaStore.Audio.Media.TITLE,
            MediaStore.Audio.Media.DATA
        )

        val cursor : Cursor? = contentResolver.query(MediaStore.Audio.Media.EXTERNAL_CONTENT_URI,
        projection, selection, null, null)

        while (cursor!!.moveToNext()){
            list.add(Song(cursor.getString(0), cursor.getString(1),
            cursor.getString(2)))
        }
        cursor.close()

        layoutManager = LinearLayoutManager(this)
        adapter = SongAdapter(list)
        rvSong.layoutManager = layoutManager
        rvSong.adapter = adapter
    }
    companion object{
        const val REQUEST_CODE = 1
    }

    @RequiresApi(Build.VERSION_CODES.R)
    private fun askPremision(){
        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.MANAGE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED){
            getSong()
        }else{
            if(ActivityCompat.shouldShowRequestPermissionRationale(this,android.Manifest.permission.MANAGE_EXTERNAL_STORAGE)){
                Toast.makeText(this, "You should allow player to read your files", Toast.LENGTH_SHORT).show()
            testTV.text = "2"
            }
            val arrayman = arrayOf("android.Manifest.permission.MANAGE_EXTERNAL_STORAGE")
            requestPermissions(arrayman, REQUEST_CODE)
            testTV.text = "3"
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        when(requestCode){
        REQUEST_CODE -> if(grantResults[0] == PackageManager.PERMISSION_GRANTED){
            getSong()
        }
            else{
                Toast.makeText(this, "Permision is not granted", Toast.LENGTH_SHORT).show()
        }


        else -> super.onRequestPermissionsResult(requestCode, permissions, grantResults)
    }}


}