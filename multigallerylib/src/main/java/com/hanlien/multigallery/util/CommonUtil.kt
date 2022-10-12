package com.hanlien.multigallery.util

import android.content.Context
import android.os.Build
import android.util.TypedValue
import android.view.WindowInsets
import android.view.WindowManager
import androidx.core.content.res.TypedArrayUtils
import com.hanlien.multigallery.listener.ImageSelectListener
import com.hanlien.multigallery.model.Image

object CommonUtil {
    internal val sendImageList = ArrayList<Image>()
    internal lateinit var listener: ImageSelectListener
    internal var numImage = 20

    var deviceWidth = 0

    fun gettingDeviceWidth(context: Context) : Int {
        if(deviceWidth == 0) {

            val windowManager =
                context.getSystemService(Context.WINDOW_SERVICE) as WindowManager

            deviceWidth = if(Build.VERSION.SDK_INT < Build.VERSION_CODES.R) { // lower than R version
                val display = windowManager.defaultDisplay

                display.width
            } else { // more than R version
                val metrics = windowManager.currentWindowMetrics
                val windowInset = metrics.windowInsets

                val insets = windowInset.getInsetsIgnoringVisibility(
                    WindowInsets.Type.navigationBars() or WindowInsets.Type.displayCutout()
                )

                insets.left + insets.right
            }
        }

        return deviceWidth
    }

    fun dpToPx(context: Context, dp: Float): Int
        = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,dp,(context.resources.displayMetrics)).toInt()
}