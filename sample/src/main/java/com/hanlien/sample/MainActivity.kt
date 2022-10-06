package com.hanlien.sample

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.databinding.DataBindingUtil
import com.hanlien.multigallery.ImageSelectListener
import com.hanlien.multigallery.MainGalleryActivity
import com.hanlien.multigallery.MultiGallery
import com.hanlien.sample.databinding.ActivityMainBinding
import java.io.File

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val listener = object: ImageSelectListener {
            override fun getImageUrls(urls: List<String>) {
                for(i in urls) {
                    Log.d("url", "$i")
                }
            }

            override fun getImageFiles(files: List<File>) {
                for(i in files) {
                    Log.d("file", "$i")
                }
            }
        }

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        binding.testBtn.setOnClickListener {
            MultiGallery()
                .setImageNum(3)
                .setContext(this)
                .setListener(listener)
                .build()
        }
    }
}