package com.jesusr00.primerosdias.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.jesusr00.primerosdias.R
import com.jesusr00.primerosdias.models.Event
import com.jesusr00.primerosdias.utils.DateManager

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

        @SuppressLint("SetTextI18n")
        fun bind(event: Event) {
            title.text = "${event.title}\n${DateManager.getDateTime(event.startTime)}"
            description.text = event.description
        }
    }
}