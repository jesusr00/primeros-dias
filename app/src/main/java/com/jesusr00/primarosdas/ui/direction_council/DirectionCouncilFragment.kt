package com.jesusr00.primarosdas.ui.direction_council

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.jesusr00.primarosdas.adapters.DirectionCouncilAdapter
import com.jesusr00.primarosdas.adapters.GuideTeachersAdapter
import com.jesusr00.primarosdas.database.DatabaseHelper
import com.jesusr00.primarosdas.databinding.FragmentDirectionConuncilBinding
import com.jesusr00.primarosdas.models.DirectionCouncilMember
import com.jesusr00.primarosdas.models.GuideTeachers
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

    @OptIn(DelicateCoroutinesApi::class)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        var directionCouncilMembers: ArrayList<DirectionCouncilMember>? = null
        GlobalScope.launch(Dispatchers.Main) {
            directionCouncilMembers = (async { db.getAllDirectionCouncilMembers() }).await()
            binding.directionCouncilRecyclerView.adapter = DirectionCouncilAdapter(directionCouncilMembers!!)
        }
    }

}