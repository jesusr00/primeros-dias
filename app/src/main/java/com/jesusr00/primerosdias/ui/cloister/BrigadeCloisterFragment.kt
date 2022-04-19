package com.jesusr00.primerosdias.ui.cloister

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.jesusr00.primerosdias.R
import com.jesusr00.primerosdias.adapters.CloisterRecyclerviewAdapter
import com.jesusr00.primerosdias.database.DatabaseHelper
import com.jesusr00.primerosdias.databinding.FragmentBrigadeClosterBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

class BrigadeCloisterFragment : Fragment() {
    private var groupId: Int? = null
    private var _binding: FragmentBrigadeClosterBinding? = null
    private val binding get() = _binding!!
    private lateinit var db: DatabaseHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            groupId = it.getInt(GROUP_ID)
        }
        db = DatabaseHelper(requireContext())
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentBrigadeClosterBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        MainScope().launch(Dispatchers.Main) {
            val professors = (async { db.getCloisterByGroup(groupId!!) }).await()
            binding.brigadeRecyclerview.adapter = CloisterRecyclerviewAdapter(professors, childFragmentManager)
        }
    }

    companion object {
        private const val GROUP_ID = "GROUP_ID"
        @JvmStatic
        fun newInstance(groupId: Int) =
            BrigadeCloisterFragment().apply {
                arguments = Bundle().apply {
                    putInt(GROUP_ID, groupId)
                }
            }
    }
}