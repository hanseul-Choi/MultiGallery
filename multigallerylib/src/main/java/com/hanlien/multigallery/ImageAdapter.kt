package com.hanlien.multigallery

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.hanlien.multigallery.databinding.ItemImageBinding
import com.hanlien.multigallery.model.Image

class ImageAdapter(val listener: ImageSelectListener) : ListAdapter<Image, ImageAdapter.ImageViewHolder>(ImageDiffCallback()) {

    private lateinit var binding: ItemImageBinding

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageViewHolder {
        binding = ItemImageBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return ImageViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ImageViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class ImageViewHolder(private val binding: ItemImageBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(image: Image) {
            binding.image = image

            binding.imageSiv.setOnClickListener {
                if(binding.imageSelectFrameV.visibility == View.VISIBLE) {
                    binding.imageSelectFrameV.visibility = View.GONE
                } else if(binding.imageSelectFrameV.visibility == View.GONE) {
                    binding.imageSelectFrameV.visibility = View.VISIBLE
                }

                listener.selectImage(image)
            }
        }
    }
}

class ImageDiffCallback : DiffUtil.ItemCallback<Image>() {
    override fun areItemsTheSame(oldItem: Image, newItem: Image): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: Image, newItem: Image): Boolean {
        return oldItem == newItem
    }
}