package com.hanlien.multigallery

import android.net.Uri
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.google.android.material.imageview.ShapeableImageView

@BindingAdapter("bind:imageSrc")
fun bindImageSrc(imageView: ShapeableImageView, uri: Uri?) {
    uri?.let {
        Glide.with(imageView)
            .load(uri)
            .into(imageView)
    }
}