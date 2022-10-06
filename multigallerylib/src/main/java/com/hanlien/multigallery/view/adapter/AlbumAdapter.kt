package com.hanlien.multigallery.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.hanlien.multigallery.databinding.ItemAlbumBinding
import com.hanlien.multigallery.listener.AlbumClickListener
import com.hanlien.multigallery.model.Album

class AlbumAdapter(
    private val listener: AlbumClickListener
) : ListAdapter<Album, AlbumAdapter.AlbumViewHolder>(AlbumDiffCallback()) {

    lateinit var binding: ItemAlbumBinding

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AlbumViewHolder {
        binding = ItemAlbumBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return AlbumViewHolder(binding)
    }

    override fun onBindViewHolder(holder: AlbumViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class AlbumViewHolder(private val IABinding: ItemAlbumBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Album) {
            binding.album = item

            IABinding.albumImageSiv.setOnClickListener {
                listener.onClickAlbum(item.title)
//                if(IABinding.albumSelectFrameV.visibility == View.VISIBLE) {
//                    IABinding.albumSelectFrameV.visibility = View.GONE
//                } else if(IABinding.albumSelectFrameV.visibility == View.GONE) {
//                    IABinding.albumSelectFrameV.visibility = View.VISIBLE
//                }

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