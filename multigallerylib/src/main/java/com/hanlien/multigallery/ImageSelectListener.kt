package com.hanlien.multigallery

import com.hanlien.multigallery.model.Image

interface ImageSelectListener {
    fun selectImage(image: Image)
}