package com.jesusr00.primerosdias.ui.cloister

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.tabs.TabLayoutMediator
import com.jesusr00.primerosdias.R
import com.jesusr00.primerosdias.adapters.CloisterPagerAdapter
import com.jesusr00.primerosdias.databinding.FragmentCloisterBinding

class CloisterFragment : Fragment() {
    private var _binding: FragmentCloisterBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCloisterBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {
            cloisterViewPager.adapter = CloisterPagerAdapter(childFragmentManager, lifecycle)
            TabLayoutMediator(cloisterTabLayout, cloisterViewPager) { tab, position ->
                tab.text = when (position) {
                    0 -> "IDFT101"
                    1 -> "IDFT102"
                    2 -> "IDFT103"
                    3 -> "IDFT104"
                    else -> "IDFT"
                }
            }.attach()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        binding.cloisterViewPager.adapter = null
        _binding = null
    }
}