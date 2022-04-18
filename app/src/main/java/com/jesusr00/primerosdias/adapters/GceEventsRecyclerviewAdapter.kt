package com.jesusr00.primerosdias.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.jesusr00.primerosdias.R
import com.jesusr00.primerosdias.models.GceEvents

class GceEventsRecyclerviewAdapter(private val events: ArrayList<GceEvents>): RecyclerView.Adapter<GceEventsRecyclerviewAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.card_gce_events, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
       holder.name.text = events[position].name
        holder.description.text = events[position].description
    }

    override fun getItemCount(): Int = events.size

    inner class ViewHolder(val view: View): RecyclerView.ViewHolder(view) {
        val name: TextView = view.findViewById(R.id.event_name)
        val description: TextView = view.findViewById(R.id.event_description)
    }
}