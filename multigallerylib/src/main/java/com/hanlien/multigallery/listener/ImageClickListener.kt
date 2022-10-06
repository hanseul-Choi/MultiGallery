package com.hanlien.multigallery.listener

import com.hanlien.multigallery.model.Image

interface ImageClickListener {
    fun selectImage(image: Image)
}