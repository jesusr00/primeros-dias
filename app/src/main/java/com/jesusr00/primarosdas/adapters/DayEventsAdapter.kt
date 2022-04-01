package com.jesusr00.primarosdas.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.jesusr00.primarosdas.R
import com.jesusr00.primarosdas.models.Event
import java.sql.Timestamp
import java.text.SimpleDateFormat
import java.util.*

class DayEventsAdapter(private val events: List<Event>) : RecyclerView.Adapter<DayEventsAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_day_event, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = events.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val event = events[position]
        holder.bind(event)
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val title: TextView = itemView.findViewById(R.id.day_event_title)
        private val description: TextView = itemView.findViewById(R.id.day_event_description)

        private fun getDateTime(s: Long): String? {
            return try {
                val sdf = SimpleDateFormat("hh:mm a")
                val netDate = Date(s*1000)
                sdf.format(netDate)
            } catch (e: Exception) {
                e.toString()
            }
        }

        @SuppressLint("SetTextI18n")
        fun bind(event: Event) {
            title.text = "${event.title}\n${getDateTime(event.startTime)}"
            description.text = event.description
        }
    }
}