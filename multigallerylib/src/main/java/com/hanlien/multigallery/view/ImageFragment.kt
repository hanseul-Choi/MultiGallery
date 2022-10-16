package com.hanlien.multigallery.view

import android.os.Bundle
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.hanlien.multigallery.R
import com.hanlien.multigallery.util.CommonUtil.sendImageList
import com.hanlien.multigallery.view.adapter.ImageAdapter
import com.hanlien.multigallery.listener.ImageClickListener
import com.hanlien.multigallery.util.ImageGetter
import com.hanlien.multigallery.model.Image
import java.lang.Exception

class ImageFragment : Fragment(), ImageClickListener {
private lateinit var imageFragmentView: View
    private lateinit var result: String

    var imageList = ArrayList<Image>()

    private lateinit var imageListRv: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        result = arguments?.getString("album", "") ?: ""

        imageFragmentView =  inflater.inflate(R.layout.fragment_image, container, false)

        findViewsId()

        return imageFragmentView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = ImageAdapter(this)

        imageList.clear()
        sendImageList.clear()

        getImages()

        adapter.submitList(
            imageList
        )

        imageListRv.adapter = adapter
    }

    private fun findViewsId() {
        imageListRv = imageFragmentView.findViewById(R.id.image_list_rv)
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
//                    val id = cursor.getLong(imageId)
//                    val title = cursor.getString(imageTitle)
                    val data = cursor.getString(imageData)

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