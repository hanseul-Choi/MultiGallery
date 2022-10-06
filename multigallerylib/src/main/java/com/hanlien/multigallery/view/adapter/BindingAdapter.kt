package com.hanlien.multigallery

import android.graphics.BitmapFactory
import android.net.Uri
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.google.android.material.imageview.ShapeableImageView
import java.io.File

@BindingAdapter("bind:imageSrc")
fun bindImageSrc(imageView: ShapeableImageView, uri: Uri?) {
    uri?.let {
        Glide.with(imageView)
            .load(uri)
            .into(imageView)
    }
}

@BindingAdapter("bind:imageFile")
fun bindImageFile(imageView: ShapeableImageView, file: File?) {
    file?.let {
        imageView.setImageBitmap(
            BitmapFactory.decodeFile(
                it.absolutePath
            )
        )
    }
}

@BindingAdapter("bind:imagePath")
fun bindImagePath(imageView: ShapeableImageView, path: String?) {
    path?.let {
        Glide.with(imageView)
            .load(File(path))
            .into(imageView)
    }
}