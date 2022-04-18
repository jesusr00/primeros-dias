package com.jesusr00.primerosdias.ui.events

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.jesusr00.primerosdias.adapters.EventsPagerAdapter
import com.jesusr00.primerosdias.databinding.FragmentEventsBinding

class EventsFragment : Fragment() {

    private var _binding: FragmentEventsBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentEventsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val eventsPagerAdapter = EventsPagerAdapter(childFragmentManager, lifecycle)
        binding.eventsVewPager.adapter = eventsPagerAdapter
        val tabLayoutMediator = TabLayoutMediator(binding.eventsTabLayout, binding.eventsVewPager) { tab, position ->
            tab.text = eventsPagerAdapter.getPageTitle(position)
        }
        tabLayoutMediator.attach()
        binding.eventsTabLayout.tabMode = TabLayout.MODE_SCROLLABLE;
    }

    override fun onDestroy() {
        super.onDestroy()
        binding.eventsVewPager.adapter = null
        _binding = null
    }
}