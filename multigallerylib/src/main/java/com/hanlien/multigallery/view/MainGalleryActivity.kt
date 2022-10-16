package com.hanlien.multigallery.view

import android.Manifest
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.widget.Button
import android.widget.FrameLayout
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.google.android.material.appbar.MaterialToolbar
import com.hanlien.multigallery.util.CommonUtil
import com.hanlien.multigallery.util.CommonUtil.sendImageList
import com.hanlien.multigallery.util.Constants
import com.hanlien.multigallery.R
import java.io.File

/**
 *  -- 작업 순서 --
 *  1. 사진 선택 되었을 때, 이미지 선택 표시 (view)
 *  1.1 imageButton selected로 custom
 *  2. Contents Provider를 이용하여 갤러리 가져오기
 *  3. 사진 선택 되었을 때, Add 클릭시 FileUri로 정보 저장
 *  4. 사진 선택 limit 선택 기능 추가
 */

/**
 *  Builder 패턴으로 Title이나 button 이름 그리고 limit image 설명문을 넣으면 괜찮을 것 같음
 */

class MainGalleryActivity : AppCompatActivity() {

    private lateinit var fragmentContainerFl: FrameLayout
    private lateinit var mainTopBarTb: MaterialToolbar
    private lateinit var selectBtn: Button

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_multigallery_main)

        // initial measurement of width - for future ^-^
//        CommonUtil.gettingDeviceWidth(this)

        findViewsId()

        // 권한 확인
        val writePermission = ContextCompat.checkSelfPermission(
            this,
            Manifest.permission.WRITE_EXTERNAL_STORAGE
        )

        val readPermission = ContextCompat.checkSelfPermission(
            this,
            Manifest.permission.READ_EXTERNAL_STORAGE
        )

        if (writePermission == PackageManager.PERMISSION_DENIED) {
            shouldShowRequestPermissionRationale(Manifest.permission.WRITE_EXTERNAL_STORAGE)

            requestPermissions(
                arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE),
                Constants.PERMISSION_WRITE_REQUEST_CODE
            )
        }

        if (readPermission == PackageManager.PERMISSION_DENIED) {
            requestPermissions(
                arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE),
                Constants.PERMISSION_READ_REQUEST_CODE
            )
        }

        settingInitFragment()
        settingListener()
    }

    private fun findViewsId() {
        fragmentContainerFl = findViewById(R.id.fragment_container_fl)
        mainTopBarTb = findViewById(R.id.main_topbar_tb)
        selectBtn = findViewById(R.id.select_btn)
    }

    private fun settingInitFragment() {
        supportFragmentManager.beginTransaction().add(
            fragmentContainerFl.id,
            AlbumFragment(),
            Constants.ALBUM_FRAGMENT_ID
        ).commitAllowingStateLoss()
    }

    private fun settingListener() {

        // back button
        mainTopBarTb.setNavigationOnClickListener {
            if (supportFragmentManager.findFragmentByTag(Constants.IMAGE_FRAGMENT_ID)?.isVisible == true) {
                moveToAlbumView()
            } else if (supportFragmentManager.findFragmentByTag(Constants.ALBUM_FRAGMENT_ID)?.isVisible == true) {
                finish()
            }
        }

        // Add button
        selectBtn.setOnClickListener {
            if (supportFragmentManager.findFragmentByTag(Constants.IMAGE_FRAGMENT_ID)?.isVisible == true) {
                // Image Add 작업
                val imageUrls = ArrayList<String>()
                val imageFiles = ArrayList<File>()

                for(i in sendImageList) {
                    imageUrls.add(i.path ?: "")
                    i.path?.let { it1 -> File(it1) }?.let { it2 -> imageFiles.add(it2) }
                }

                val sendImageSize = sendImageList.size

                if(sendImageSize != 0 && sendImageSize <= 20  && sendImageSize != CommonUtil.numImage) {
                    CommonUtil.listener.getImageUrls(imageUrls)
                    CommonUtil.listener.getImageFiles(imageFiles)
                    finish()
                } else {
                    Toast.makeText(this, "The number of images must be greater than 0 and less than ${CommonUtil.numImage} and less than 20", Toast.LENGTH_SHORT).show()
                }
            } else if (supportFragmentManager.findFragmentByTag(Constants.ALBUM_FRAGMENT_ID)?.isVisible == true) {
//                moveToImageView()
            }
        }
    }

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        when (requestCode) {
            Constants.PERMISSION_WRITE_REQUEST_CODE -> {
                if (grantResults.isNotEmpty()) {
                    if(grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                        moveToAlbumView()
                    } else {
                        Toast.makeText(this, "Please allow your permission", Toast.LENGTH_SHORT).show()

                        finish()
                    }
                }

                return
            }
        }
    }

    internal fun moveToImageView(albumTitle: String) {
        val bundle = Bundle().apply {
            putString("album", albumTitle)
        }

        supportFragmentManager.beginTransaction()
            .replace(
                fragmentContainerFl.id,
                ImageFragment().apply {
                      arguments = bundle
                },
                Constants.IMAGE_FRAGMENT_ID
            )
            .commitAllowingStateLoss()

        mainTopBarTb.title = Constants.IMAGE_TITLE
        selectBtn.text = Constants.SEND_DESC
    }

    private fun moveToAlbumView() {
        supportFragmentManager.beginTransaction().replace(
            fragmentContainerFl.id,
            AlbumFragment(),
            Constants.ALBUM_FRAGMENT_ID
        ).commitAllowingStateLoss()

        mainTopBarTb.title = Constants.ALBUM_TITLE
        selectBtn.text = ""
    }

    override fun onBackPressed() {
        if (supportFragmentManager.findFragmentByTag(Constants.IMAGE_FRAGMENT_ID)?.isVisible == true) {
            moveToAlbumView()
        } else {
            finish()
        }
    }
}
