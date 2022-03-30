package com.jesusr00.primarosdas.ui.events

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.jesusr00.primarosdas.adapters.EventsPagerAdapter
import com.jesusr00.primarosdas.databinding.FragmentEventsBinding

class EventsFragment : Fragment() {

    private var _binding: FragmentEventsBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentEventsBinding.inflate(inflater, container, false)

        val eventsPagerAdapter = EventsPagerAdapter(requireActivity().supportFragmentManager, lifecycle)
        binding.eventsVewPager.adapter = eventsPagerAdapter
        TabLayoutMediator(binding.eventsTabLayout, binding.eventsVewPager) { tab, position ->
            tab.text = eventsPagerAdapter.getPageTitle(position)
        }.attach()
        binding.eventsTabLayout.tabMode = TabLayout.MODE_SCROLLABLE;
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}