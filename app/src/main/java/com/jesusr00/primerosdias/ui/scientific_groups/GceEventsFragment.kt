package com.jesusr00.primerosdias.ui.scientific_groups

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.jesusr00.primerosdias.adapters.GceActivityRecyclerviewAdapter
import com.jesusr00.primerosdias.adapters.GceEventsRecyclerviewAdapter
import com.jesusr00.primerosdias.databinding.FragmentGceEventsBinding
import com.jesusr00.primerosdias.models.GceEvents
import com.jesusr00.primerosdias.utils.CustomAssetsManager

class GceEventsFragment : Fragment() {

    private var _binding: FragmentGceEventsBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentGceEventsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val events: ArrayList<GceEvents> = ArrayList()
        val customAssetsManager = CustomAssetsManager(requireContext())
        val rawActivities = customAssetsManager.getCGEInfo().getJSONArray("gce_events")
        for (i in 0 until rawActivities.length()) {
            Log.d("GCE_ACTIVITY", rawActivities.getJSONObject(i).toString())
            val activity = GceEvents(
                rawActivities.getJSONObject(i).getString("name"),
                rawActivities.getJSONObject(i).getString("description"),
            )
            events.add(activity)
        }
        binding.gceEventsRecyclerview.adapter = GceEventsRecyclerviewAdapter(events)

    }
}