package com.hanlien.multigallery.view

import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.hanlien.multigallery.R
import com.hanlien.multigallery.view.adapter.AlbumAdapter
import com.hanlien.multigallery.listener.AlbumClickListener
import com.hanlien.multigallery.util.ImageGetter
import com.hanlien.multigallery.model.Album
import java.lang.Exception
import kotlin.collections.ArrayList

class AlbumFragment : Fragment(), AlbumClickListener {
    private lateinit var albumFragmentView: View
    private lateinit var albumListRv: RecyclerView

    var albumList = ArrayList<Album>()
    var albumBucket = ArrayList<String>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        albumFragmentView = inflater.inflate(R.layout.fragment_album, container, false)

        findViewsId()

        return albumFragmentView
    }

    private fun findViewsId() {
        albumListRv = albumFragmentView.findViewById(R.id.album_list_rv)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = AlbumAdapter(this)

        getAlbums()

        adapter.submitList(
            albumList
        )

        albumListRv.adapter = adapter
    }

    private fun getAlbums() {
        try {
            val cursor = ImageGetter(
                activity?.contentResolver
            ).getAlbumCursor()

            if (cursor.moveToLast()) {
                do {
                    //1. 각 컬럼의 열 인덱스를 취득한다.
                    val bucketId = cursor.getColumnIndexOrThrow(MediaStore.Images.ImageColumns.BUCKET_ID)
                    val bucketName =
                        cursor.getColumnIndexOrThrow(MediaStore.Images.ImageColumns.BUCKET_DISPLAY_NAME)
                    val data =
                        cursor.getColumnIndexOrThrow(MediaStore.Images.ImageColumns.DATA)

                    //2. 인덱스를 바탕으로 데이터를 Cursor로부터 취득하기
                    val ids = cursor.getLong(bucketId)
                    val bucketNames = cursor.getString(bucketName)
                    val datas = cursor.getString(data)

                    Log.d("imageUri", "$ids, $bucketNames, $datas")

                    if(!albumBucket.contains(bucketNames)){
                        albumList.add(
                            Album(
                                path = datas,
                                title = bucketNames
                            )
                        )
                        albumBucket.add(bucketNames)
                    }
                } while (cursor.moveToPrevious())
            }

            cursor.close()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    override fun onClickAlbum(item: String) {
        (activity as MainGalleryActivity).moveToImageView(item)
    }
}