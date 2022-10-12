package com.hanlien.multigallery.view.adapter

import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.setPadding
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.imageview.ShapeableImageView
import com.hanlien.multigallery.R
import com.hanlien.multigallery.listener.ImageClickListener
import com.hanlien.multigallery.model.Image
import com.hanlien.multigallery.util.CommonUtil

class ImageAdapter(val listener: ImageClickListener) : ListAdapter<Image, ImageAdapter.ImageViewHolder>(
    ImageDiffCallback()
) {
    private lateinit var itemImageView: View

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageViewHolder {
        itemImageView = LayoutInflater.from(parent.context).inflate(R.layout.item_image, parent, false)

        val layoutParams = itemImageView.layoutParams
        layoutParams.width = (parent.width * 0.33).toInt()
        itemImageView.layoutParams = layoutParams
        itemImageView.setPadding(CommonUtil.dpToPx(itemImageView.context, 5f))

        return ImageViewHolder(itemImageView)
    }

    override fun onBindViewHolder(holder: ImageViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class ImageViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private lateinit var imageSiv: ShapeableImageView
        private lateinit var imageSelectFrameV: View

        fun bind(image: Image) {
            imageSiv = itemView.findViewById(R.id.image_siv)
            imageSelectFrameV = itemView.findViewById(R.id.image_select_frame_v)

            image.path?.let {
                imageSiv.setImageURI(Uri.parse(it))
            }

            imageSiv.setOnClickListener {
                if(imageSelectFrameV.visibility == View.VISIBLE) {
                    imageSelectFrameV.visibility = View.GONE
                } else if(imageSelectFrameV.visibility == View.GONE) {
                    imageSelectFrameV.visibility = View.VISIBLE
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