package com.jesusr00.primarosdas.ui.events

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.tabs.TabLayout
import com.jesusr00.primarosdas.adapters.DayEventsAdapter
import com.jesusr00.primarosdas.database.DatabaseHelper
import com.jesusr00.primarosdas.databinding.FragmentDayDetailsBinding
import com.jesusr00.primarosdas.models.Event
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class DayDetailsFragment : Fragment() {

    private var dayEventsKey: Int? = null
    private var _binding: FragmentDayDetailsBinding? = null
    private val binding get() = _binding!!
    private var db: DatabaseHelper? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            dayEventsKey = it.getInt(DAY_EVENTS, 0)
        }
        db = DatabaseHelper(requireContext())
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentDayDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    @OptIn(DelicateCoroutinesApi::class)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        var dayEvents: ArrayList<Event>?
        GlobalScope.launch(Dispatchers.Main) {
            dayEvents = db?.getEventsByDayId(dayEventsKey!!)
            binding.dayEventsRecyclerView.adapter = DayEventsAdapter(dayEvents!!)
        }
    }

    companion object {
        private const val DAY_EVENTS = "DAY_EVENTS"

        @JvmStatic
        fun newInstance(dayEvents: Int) =
            DayDetailsFragment().apply {
                arguments = Bundle().apply {
                    putInt(DAY_EVENTS, dayEvents)
                }
            }
    }
}

