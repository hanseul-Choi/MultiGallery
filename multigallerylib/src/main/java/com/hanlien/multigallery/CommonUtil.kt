package com.hanlien.multigallery

import com.hanlien.multigallery.model.Image

object CommonUtil {
    internal val sendImageList = ArrayList<Image>()
    internal lateinit var listener: ImageSelectListener
    internal var numImage = 10
}