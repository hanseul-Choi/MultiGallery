package com.hanlien.multigallery

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.hanlien.multigallery.databinding.ItemAlbumBinding

class AlbumAdapter : ListAdapter<Int, AlbumAdapter.AlbumViewHolder>(AlbumDiffCallback()) {

    lateinit var binding: ItemAlbumBinding

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AlbumViewHolder {
        binding = ItemAlbumBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return AlbumViewHolder(binding)
    }

    override fun onBindViewHolder(holder: AlbumViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class AlbumViewHolder(binding: ItemAlbumBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(image: Int) {

        }
    }
}

class AlbumDiffCallback: DiffUtil.ItemCallback<Int>() {
    override fun areItemsTheSame(oldItem: Int, newItem: Int): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: Int, newItem: Int): Boolean {
        return oldItem == newItem
    }

}