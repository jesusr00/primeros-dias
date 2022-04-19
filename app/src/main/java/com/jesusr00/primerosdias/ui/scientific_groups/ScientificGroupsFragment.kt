package com.jesusr00.primerosdias.ui.scientific_groups

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.google.android.material.tabs.TabLayoutMediator
import com.jesusr00.primerosdias.adapters.GcePagerAdapter
import com.jesusr00.primerosdias.databinding.FragmentScientificGroupsBinding

class ScientificGroupsFragment : Fragment() {

    private var _binding: FragmentScientificGroupsBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentScientificGroupsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {
            gceVewPager.adapter = GcePagerAdapter(childFragmentManager, lifecycle)
            TabLayoutMediator(gceTabLayout, gceVewPager) { tab, position ->
                tab.text = when (position) {
                    0 -> "GCE"
                    1 -> "Actividades"
                    2 -> "Eventos"
                    else -> "GCE"
                }
            }.attach()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        binding.gceVewPager.adapter = null
        _binding = null
    }

}