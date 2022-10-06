package com.hanlien.multigallery

import com.hanlien.multigallery.model.Image

interface ImageClickListener {
    fun selectImage(image: Image)
}