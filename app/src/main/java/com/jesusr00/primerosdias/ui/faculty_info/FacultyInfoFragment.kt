package com.jesusr00.primerosdias.ui.faculty_info

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.MediaController
import androidx.core.net.toUri
import com.jesusr00.primerosdias.R
import com.jesusr00.primerosdias.databinding.FragmentFacultyInfoBinding
import com.jesusr00.primerosdias.utils.CustomAssetsManager
import java.io.File

class FacultyInfoFragment : Fragment() {

    private lateinit var _binding: FragmentFacultyInfoBinding
    private val binding get() = _binding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFacultyInfoBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val videos = CustomAssetsManager(requireContext()).getVideos()
        val mediaController = MediaController(requireContext())

        binding.videoView.apply {
            setVideoURI(videos.filter { file: File -> file.name.contains("lobos.mp4") }[0].toUri())
            mediaController.setAnchorView(binding.mediaAnchor)
            mediaController.setMediaPlayer(this)
            setMediaController(mediaController)
            start()
        }

    }
}