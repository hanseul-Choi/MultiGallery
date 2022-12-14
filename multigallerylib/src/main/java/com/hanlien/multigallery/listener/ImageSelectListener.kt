package com.hanlien.multigallery.listener

import java.io.File

interface ImageSelectListener {
    fun getImageUrls(urls: List<String>)
    fun getImageFiles(files: List<File>)
}