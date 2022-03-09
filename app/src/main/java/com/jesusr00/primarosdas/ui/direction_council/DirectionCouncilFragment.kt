package com.jesusr00.primarosdas.ui.direction_council

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.jesusr00.primarosdas.adapters.DirectionCouncilAdapter
import com.jesusr00.primarosdas.databinding.FragmentDirectionConuncilBinding
import com.jesusr00.primarosdas.models.DirectionCouncilMember

class DirectionCouncilFragment : Fragment() {

    private var _binding: FragmentDirectionConuncilBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDirectionConuncilBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val dcm = ArrayList<DirectionCouncilMember>()
        dcm.add(DirectionCouncilMember("Reina Victoria", "Estrada Nelson", "restrada", null, "Decana"))

        binding.directionCouncilRecyclerView.adapter = DirectionCouncilAdapter(dcm)
    }

}