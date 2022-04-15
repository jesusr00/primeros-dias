package com.jesusr00.primerosdias.ui.direction_council

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.jesusr00.primerosdias.adapters.DirectionCouncilAdapter
import com.jesusr00.primerosdias.database.DatabaseHelper
import com.jesusr00.primerosdias.databinding.FragmentDirectionConuncilBinding
import com.jesusr00.primerosdias.models.DirectionCouncilMember
import kotlinx.coroutines.*

class DirectionCouncilFragment : Fragment() {

    private var _binding: FragmentDirectionConuncilBinding? = null
    private val binding get() = _binding!!
    private lateinit var db: DatabaseHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        db = DatabaseHelper(requireContext())
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDirectionConuncilBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        MainScope().launch(Dispatchers.Main) {
            val directionCouncilMembers = (async { db.getAllDirectionCouncilMembers() }).await()
            binding.directionCouncilRecyclerView.adapter = DirectionCouncilAdapter(directionCouncilMembers, childFragmentManager)
            binding.directionCouncilProgress.visibility = View.GONE
            binding.directionCouncilRecyclerView.visibility = View.VISIBLE
        }
    }

}