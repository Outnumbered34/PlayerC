package com.example.player

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class SongAdapter2(val list : MutableList<Song>) : RecyclerView.Adapter<SongAdapter2.SongViewHolder>() {
    class SongViewHolder( v: View):RecyclerView.ViewHolder(v), View.OnClickListener {
        lateinit var song: Song
        lateinit var tvArtist: TextView
        lateinit var tvSong: TextView

        init{
            tvArtist = v.findViewById(R.id.tvArtist)
            tvSong = v.findViewById(R.id.tvSong)

            v.setOnClickListener(this)
        }

        fun onBind(song: Song){
            this.song = song
            tvArtist.text = song.songArtist
            tvSong.text = song.songName
        }


        override fun onClick(v: View?) {
            TODO("Not yet implemented")
        }


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SongAdapter2.SongViewHolder {
        val context = parent.context
        val rowView = LayoutInflater.from(context).inflate(R.layout.one_song, parent, false)

        return SongViewHolder(rowView)
    }

    override fun onBindViewHolder(holder: SongAdapter2.SongViewHolder, position: Int) {
        val item = list[position]
        holder.onBind(item)
    }

    override fun getItemCount(): Int {
        TODO("Not yet implemented")
    }
}