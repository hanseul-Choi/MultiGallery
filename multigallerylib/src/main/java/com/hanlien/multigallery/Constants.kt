package com.hanlien.multigallery

import com.hanlien.multigallery.model.Image

object Constants {
    const val ALBUM_FRAGMENT_ID = "ALBUM"
    const val IMAGE_FRAGMENT_ID = "IMAGE"

    const val ADD_DESC = "ADD"
    const val SEND_DESC = "SEND"

    const val ALBUM_TITLE = "Select Album"
    const val IMAGE_TITLE = "Select Images"

    const val PERMISSION_WRITE_REQUEST_CODE = 1000
    const val PERMISSION_READ_REQUEST_CODE = 1001

    val sendImageList = ArrayList<Image>()
}