package com.hanlien.multigallery.model

import android.net.Uri
import java.io.File

data class Album(
    val uri: Uri? = null,
    val file: File? = null,
    val path: String? = null,
    val isSelected : Boolean = false,
    val title: String = ""
)
