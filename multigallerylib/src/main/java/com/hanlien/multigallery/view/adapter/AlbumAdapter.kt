package com.hanlien.multigallery.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.google.android.material.imageview.ShapeableImageView
import com.hanlien.multigallery.R
import com.hanlien.multigallery.listener.AlbumClickListener
import com.hanlien.multigallery.model.Album
import java.io.File

class AlbumAdapter(
    private val listener: AlbumClickListener
) : ListAdapter<Album, AlbumAdapter.AlbumViewHolder>(AlbumDiffCallback()) {
    private lateinit var albumView: View

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AlbumViewHolder {
        albumView = LayoutInflater.from(parent.context).inflate(R.layout.item_album, parent, false)

        return AlbumViewHolder(albumView)
    }

    override fun onBindViewHolder(holder: AlbumViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class AlbumViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(item: Album) {
            val albumImageSiv: ShapeableImageView = itemView.findViewById(R.id.album_image_siv)
            val albumSelectTitleTv: TextView = itemView.findViewById(R.id.album_select_title_tv)

            albumSelectTitleTv.text = item.title

            item.path?.let {
                Glide.with(albumImageSiv)
                    .load(File(it))
                    .into(albumImageSiv)
            }

            albumImageSiv.setOnClickListener {
                listener.onClickAlbum(item.title)
            }
        }
    }
}

class AlbumDiffCallback: DiffUtil.ItemCallback<Album>() {
    override fun areItemsTheSame(oldItem: Album, newItem: Album): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: Album, newItem: Album): Boolean {
        return oldItem == newItem
    }
}