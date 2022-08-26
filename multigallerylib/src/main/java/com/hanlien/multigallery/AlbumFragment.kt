package com.hanlien.multigallery

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.hanlien.multigallery.databinding.FragmentAlbumBinding
import com.hanlien.multigallery.databinding.FragmentImageBinding

class AlbumFragment : Fragment() {

    private lateinit var binding: FragmentAlbumBinding

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
    }
}