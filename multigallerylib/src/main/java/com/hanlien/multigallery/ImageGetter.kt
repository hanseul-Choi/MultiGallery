package com.hanlien.multigallery

import android.content.ContentResolver
import android.database.Cursor
import android.os.Build
import android.provider.MediaStore
import androidx.annotation.RequiresApi

class ImageGetter(private val resolver: ContentResolver?) {
    fun getImageCursor(album: String): Cursor {
        val what = arrayOf(
            MediaStore.Images.ImageColumns._ID,
            MediaStore.Images.ImageColumns.TITLE,
            MediaStore.Images.ImageColumns.DATA
        )

        return resolver?.query(
            MediaStore.Images.Media.EXTERNAL_CONTENT_URI, what, MediaStore.Images.Media.BUCKET_DISPLAY_NAME + " =?", arrayOf(album), MediaStore.Images.Media.DATE_ADDED)!!
    }

    fun getAlbumCursor() : Cursor {
        val what = arrayOf(
            MediaStore.Images.Media.BUCKET_ID,
            MediaStore.Images.Media.BUCKET_DISPLAY_NAME,
            MediaStore.Images.Media.DATA
        )

        return resolver?.query(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, what, null, null, MediaStore.Images.Media.DATE_ADDED)!!
    }
}