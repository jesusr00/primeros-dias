package com.jesusr00.primarosdas.ui.guide_teachers

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.jesusr00.primarosdas.adapters.GuideTeachersAdapter
import com.jesusr00.primarosdas.database.DatabaseHelper
import com.jesusr00.primarosdas.databinding.FragmentGuideTeachersBinding
import com.jesusr00.primarosdas.models.GuideTeachers
import kotlinx.coroutines.*

class GuideTeachersFragment : Fragment() {

    private lateinit var db: DatabaseHelper
    private var _binding: FragmentGuideTeachersBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        db = DatabaseHelper(requireContext())
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentGuideTeachersBinding.inflate(inflater, container, false)
        return binding.root
    }

    @OptIn(DelicateCoroutinesApi::class)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var guideTeachers: ArrayList<GuideTeachers>?
        GlobalScope.launch(Dispatchers.Main) {
            guideTeachers = (async { db.getAllGuideTeachers() }).await()
            binding.guideTeachersRecyclerview.adapter = GuideTeachersAdapter(guideTeachers!!)
            binding.guideTeachersProgress.visibility = View.GONE
            binding.guideTeachersRecyclerview.visibility = View.VISIBLE
        }
    }
}