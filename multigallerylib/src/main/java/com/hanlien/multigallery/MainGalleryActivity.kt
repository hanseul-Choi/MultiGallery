package com.hanlien.multigallery

import android.Manifest
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import com.hanlien.multigallery.databinding.ActivityMultigalleryMainBinding

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

    private lateinit var binding: ActivityMultigalleryMainBinding

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // 권한 확인
        val writePermission = ContextCompat.checkSelfPermission(
            this,
            Manifest.permission.WRITE_EXTERNAL_STORAGE
        )

        val readPermission = ContextCompat.checkSelfPermission(
            this,
            Manifest.permission.READ_EXTERNAL_STORAGE
        )

        if(writePermission == PackageManager.PERMISSION_DENIED) {
            requestPermissions(
                arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE),
                Constants.PERMISSION_WRITE_REQUEST_CODE
            )
        }

        if(readPermission == PackageManager.PERMISSION_DENIED) {
            requestPermissions(
                arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE),
                Constants.PERMISSION_READ_REQUEST_CODE
            )
        }

        binding = DataBindingUtil.setContentView(this, R.layout.activity_multigallery_main)

        settingInitFragment()
        settingListener()
    }

    private fun settingInitFragment() {
        supportFragmentManager.beginTransaction().add(
            binding.fragmentContainerFl.id,
            AlbumFragment(),
            Constants.ALBUM_FRAGMENT_ID
        ).commitAllowingStateLoss()
    }

    private fun settingListener() {

        // back button
        binding.mainTopbarTb.setNavigationOnClickListener {
            Toast.makeText(this, "click back", Toast.LENGTH_SHORT).show()

            if(supportFragmentManager.findFragmentByTag(Constants.IMAGE_FRAGMENT_ID)?.isVisible == true) {
                moveToAlbumView()
            } else if(supportFragmentManager.findFragmentByTag(Constants.ALBUM_FRAGMENT_ID)?.isVisible == true) {
                finish()
            }
        }

        // Add button
        binding.selectBtn.setOnClickListener {
            Toast.makeText(this, "click add", Toast.LENGTH_SHORT).show()

            if(supportFragmentManager.findFragmentByTag(Constants.IMAGE_FRAGMENT_ID)?.isVisible == true) {
                // Image Add 작업

            } else if(supportFragmentManager.findFragmentByTag(Constants.ALBUM_FRAGMENT_ID)?.isVisible == true) {
                moveToImageView()
            }
        }
    }

    private fun moveToImageView() {
        supportFragmentManager.beginTransaction()
            .replace(binding.fragmentContainerFl.id,
                ImageFragment(),
                Constants.IMAGE_FRAGMENT_ID)
            .commitAllowingStateLoss()

        binding.mainTopbarTb.title = Constants.IMAGE_TITLE
        binding.selectBtn.text = Constants.SEND_DESC
    }

    private fun moveToAlbumView() {
        supportFragmentManager.beginTransaction().replace(
            binding.fragmentContainerFl.id,
            AlbumFragment(),
            Constants.ALBUM_FRAGMENT_ID
        ).commitAllowingStateLoss()

        binding.mainTopbarTb.title = Constants.ALBUM_TITLE
        binding.selectBtn.text = Constants.ADD_DESC
    }
}