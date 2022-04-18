package com.jesusr00.primerosdias.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.jesusr00.primerosdias.R
import com.jesusr00.primerosdias.models.GceEvents

class GceActivityRecyclerviewAdapter(private val activities: ArrayList<String>): RecyclerView.Adapter<GceActivityRecyclerviewAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.card_gce_activity, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.activity.text = activities[position]
    }

    override fun getItemCount(): Int = activities.size

    inner class ViewHolder(val view: View): RecyclerView.ViewHolder(view) {
        val activity: TextView = view.findViewById(R.id.gce_activity)
    }
}