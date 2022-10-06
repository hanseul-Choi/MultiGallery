package com.hanlien.multigallery

import android.content.Context
import android.content.Intent

/**
 *  bridge code
 */
class MultiGallery {
    private var context: Context? = null

    fun setContext(context: Context) : MultiGallery {
        this.context = context

        return this
    }

    fun setImageNum(num: Int) : MultiGallery {
        CommonUtil.numImage = num

        return this
    }

    fun setListener(listener: ImageSelectListener) : MultiGallery {
        CommonUtil.listener = listener

        return this
    }

    fun build() : MultiGallery {
        if(context == null) {
            throw NullPointerException("context is null")
        } else {
            val intent = Intent(context, MainGalleryActivity::class.java)
            context?.startActivity(intent)
        }

        return this
    }
}