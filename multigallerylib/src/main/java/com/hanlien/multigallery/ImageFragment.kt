package com.hanlien.multigallery

import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.hanlien.multigallery.CommonUtil.sendImageList
import com.hanlien.multigallery.databinding.FragmentImageBinding
import com.hanlien.multigallery.model.Image
import java.lang.Exception

class ImageFragment : Fragment(), ImageClickListener {

    private lateinit var binding: FragmentImageBinding
    private lateinit var result: String

    var imageList = ArrayList<Image>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentImageBinding.inflate(inflater, container, false)

        result = arguments?.getString("album", "") ?: ""

        Toast.makeText(requireContext(), result, Toast.LENGTH_SHORT).show()

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = ImageAdapter(this)

        getImages()

        adapter.submitList(
            imageList
        )

        binding.imageListRv.adapter = adapter
    }

    private fun getImages() {
        try {
            val cursor = ImageGetter(
                activity?.contentResolver
            ).getImageCursor(album = result)

            if (cursor.moveToLast()) {
                do {
                    //1. 각 컬럼의 열 인덱스를 취득한다.
                    val imageId = cursor.getColumnIndexOrThrow(MediaStore.Images.ImageColumns._ID)
                    val imageTitle =
                        cursor.getColumnIndexOrThrow(MediaStore.Images.ImageColumns.TITLE)
                    val imageData =
                        cursor.getColumnIndexOrThrow(MediaStore.Images.ImageColumns.DATA)

                    //2. 인덱스를 바탕으로 데이터를 Cursor로부터 취득하기
                    val id = cursor.getLong(imageId)
                    val title = cursor.getString(imageTitle)
                    val data = cursor.getString(imageData)

                    Log.d("imageUri", "$id, $title, $data")

                    imageList.add(
                        Image(
                            path = data
                        )
                    )

                } while (cursor.moveToPrevious())
            }

            cursor.close()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    // 어댑터에서 이미지 선택 시 호출
    override fun selectImage(image: Image) {
        if(!sendImageList.contains(image)) {
            sendImageList.add(image)
        } else {
            sendImageList.remove(image)
        }
    }
}