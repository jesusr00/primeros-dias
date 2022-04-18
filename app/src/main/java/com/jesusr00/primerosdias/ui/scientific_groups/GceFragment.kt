package com.jesusr00.primerosdias.ui.scientific_groups

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.jesusr00.primerosdias.databinding.FragmentGceBinding
import com.jesusr00.primerosdias.utils.CustomAssetsManager

class GceFragment : Fragment() {

    private var _binding: FragmentGceBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentGceBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val assetsManager = CustomAssetsManager(requireContext())
        val gceInfo = assetsManager.getCGEInfo()
        binding.cgeDescription.text = gceInfo.getString("gce_info")
    }
}