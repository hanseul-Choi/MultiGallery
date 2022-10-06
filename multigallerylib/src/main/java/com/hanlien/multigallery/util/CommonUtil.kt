package com.hanlien.multigallery.util

import com.hanlien.multigallery.listener.ImageSelectListener
import com.hanlien.multigallery.model.Image

object CommonUtil {
    internal val sendImageList = ArrayList<Image>()
    internal lateinit var listener: ImageSelectListener
    internal var numImage = 20
}