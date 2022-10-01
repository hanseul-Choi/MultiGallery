package com.hanlien.multigallery

import android.content.ContentResolver
import android.database.Cursor
import android.os.Build
import android.provider.MediaStore
import androidx.annotation.RequiresApi

class ImageGetter(private val resolver: ContentResolver?) {
    fun getImageCursor(): Cursor {
        var queryUri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI

        val what = arrayOf(
            MediaStore.Images.ImageColumns._ID,
            MediaStore.Images.ImageColumns.TITLE,
            MediaStore.Images.ImageColumns.DATE_TAKEN
        )

        val orderBy = MediaStore.Images.ImageColumns.DATE_TAKEN + " DESC"

        queryUri = queryUri.buildUpon().appendQueryParameter("limit", "1").build()

        return resolver?.query(queryUri, what, null, null, orderBy)!!
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