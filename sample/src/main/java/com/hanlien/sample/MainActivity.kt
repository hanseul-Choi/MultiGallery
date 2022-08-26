package com.hanlien.sample

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.hanlien.multigallery.MainGalleryActivity
import com.hanlien.sample.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        binding.testBtn.setOnClickListener {
            val intent = Intent(this, MainGalleryActivity::class.java)
            startActivity(intent)
        }
    }
}