package com.hanlien.multigallery

import android.content.ContentUris
import android.graphics.BitmapFactory
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.net.toUri
import androidx.fragment.app.Fragment
import com.hanlien.multigallery.databinding.FragmentAlbumBinding
import com.hanlien.multigallery.databinding.FragmentImageBinding
import com.hanlien.multigallery.model.Album
import java.io.File
import java.lang.Exception
import java.lang.String.format
import java.text.DateFormat
import java.util.*
import kotlin.collections.ArrayList

class AlbumFragment : Fragment() {

    private lateinit var binding: FragmentAlbumBinding

    var albumList = ArrayList<Album>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAlbumBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = AlbumAdapter()

        getAlbums()

        adapter.submitList(
            albumList
        )

        binding.albumListRv.adapter = adapter
    }

    fun getAlbums() {
        try {
            val cursor = ImageGetter(
                activity?.contentResolver
            ).getAlbumCursor()

            if (cursor.moveToLast()) {
                do {
                    //1. 각 컬럼의 열 인덱스를 취득한다.
                    val idColNum = cursor.getColumnIndexOrThrow(MediaStore.Images.ImageColumns.BUCKET_ID)
                    val bucketName =
                        cursor.getColumnIndexOrThrow(MediaStore.Images.ImageColumns.BUCKET_DISPLAY_NAME)
                    val data =
                        cursor.getColumnIndexOrThrow(MediaStore.Images.ImageColumns.DATA)

                    //2. 인덱스를 바탕으로 데이터를 Cursor로부터 취득하기
                    val id = cursor.getLong(idColNum)
                    val title = cursor.getString(bucketName)
                    val image = cursor.getString(data)

                    Log.d("imageUri", "$id, $title, $image")

                    albumList.add(
                        Album(
                            path = image
                        )
                    )

                } while (cursor.moveToPrevious())
            }

            cursor.close()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}