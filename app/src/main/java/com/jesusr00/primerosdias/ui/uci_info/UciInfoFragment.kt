package com.jesusr00.primerosdias.ui.uci_info

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.net.toUri
import com.jesusr00.primerosdias.databinding.FragmentUciInfoBinding
import com.jesusr00.primerosdias.utils.CustomAssetsManager
import java.io.File

class UciInfoFragment : Fragment() {

    private var _binding: FragmentUciInfoBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?): View? {
        _binding = FragmentUciInfoBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val images = CustomAssetsManager(requireContext()).getImages()

        binding.campusImage.setImageURI((images.filter { file: File -> file.name.equals("campus.jpg", true) })[0].toUri())
        binding.sportImage.setImageURI((images.filter { file: File -> file.name.equals("sport.jpg", true) })[0].toUri())
        binding.graduatesImage.setImageURI((images.filter { file: File -> file.name.equals("graduates.jpg", true) })[0].toUri())
        binding.eventsImage.setImageURI((images.filter { file: File -> file.name.equals("events.jpg", true) })[0].toUri())
        binding.culturalImage.setImageURI((images.filter { file: File -> file.name.equals("cultural.jpg", true) })[0].toUri())
        binding.communicationImage.setImageURI((images.filter { file: File -> file.name.equals("communication.jpg", true) })[0].toUri())
    }
}