package com.jesusr00.primerosdias.ui.events

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.navOptions
import com.jesusr00.primerosdias.adapters.DayEventsAdapter
import com.jesusr00.primerosdias.database.DatabaseHelper
import com.jesusr00.primerosdias.databinding.FragmentDayDetailsBinding
import com.jesusr00.primerosdias.models.Event
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
        db = DatabaseHelper(requireContext())
        arguments?.let {
            dayEventsKey = it.getInt(DAY_EVENTS, 0)
        }
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
            binding.dayEventsProgress.visibility = View.GONE
            binding.dayEventsRecyclerView.visibility = View.VISIBLE
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    companion object {
        private const val DAY_EVENTS = "DAY_EVENTS"

        @JvmStatic
        fun newInstance(dayEvents: Int) =
            DayDetailsFragment().apply {
                arguments = Bundle().apply {
                    putInt(DAY_EVENTS, dayEvents)
                }
                navOptions { launchSingleTop = true }
            }
    }
}

