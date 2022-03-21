package com.example.player

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class SongAdapter(var list: MutableList<Song>) : RecyclerView.Adapter<SongAdapter.SongViewHolder>() {
    class SongViewHolder(view: View) : RecyclerView.ViewHolder(view), View.OnClickListener {

        lateinit var song: Song
        lateinit var songArtist: TextView
        lateinit var songNama: TextView
        init {
            songArtist = view.findViewById(R.id.tvArtist)
            songNama = view.findViewById(R.id.tvSong)

            view.setOnClickListener(this)

        }

        fun onBind(song: Song){
            this.song = song
            songArtist.text = song.songArtist
            songNama.text = song.songName


        }




        override fun onClick(v: View?) {
            TODO("Not yet implemented")
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SongViewHolder {
        val context = parent.context
        val rowView = LayoutInflater.from(context).inflate(R.layout.one_song, parent, false)

            return SongViewHolder(rowView)
    }

    override fun onBindViewHolder(holder: SongViewHolder, position: Int) {
        val item = list[position]
        holder.onBind(item)
    }

    override fun getItemCount(): Int {
       return list.size
    }
}

