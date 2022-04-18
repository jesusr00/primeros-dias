package com.jesusr00.primerosdias.ui.scientific_groups

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.jesusr00.primerosdias.adapters.GceActivityRecyclerviewAdapter
import com.jesusr00.primerosdias.databinding.FragmentGceActivitiesBinding
import com.jesusr00.primerosdias.utils.CustomAssetsManager
import org.json.JSONArray

class GceActivitiesFragment : Fragment() {

    private var _binding: FragmentGceActivitiesBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
       _binding = FragmentGceActivitiesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val customAssetsManager = CustomAssetsManager(requireContext())
        val rawActivities: JSONArray = customAssetsManager.getCGEInfo().getJSONArray("gce_activities")
        val gceActivities: ArrayList<String> = ArrayList()
        for (i in 0 until rawActivities.length()) {
            gceActivities.add(rawActivities.getString(i))
        }
        binding.gceActivitiesRecyclerview.adapter = GceActivityRecyclerviewAdapter(gceActivities)
    }

}