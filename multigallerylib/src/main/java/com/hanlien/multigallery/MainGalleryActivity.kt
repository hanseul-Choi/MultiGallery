package com.hanlien.multigallery

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.hanlien.multigallery.databinding.ActivityMultigalleryMainBinding


/**
 *  Builder 패턴으로 Title이나 button 이름 그리고 limit image 설명문을 넣으면 괜찮을 것 같음
 */

class MainGalleryActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMultigalleryMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

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