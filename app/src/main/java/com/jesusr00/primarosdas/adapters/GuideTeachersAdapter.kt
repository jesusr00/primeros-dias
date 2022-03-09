package com.jesusr00.primarosdas.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.jesusr00.primarosdas.R
import com.jesusr00.primarosdas.models.GuideTeachers

class GuideTeachersAdapter(private val guideTeachers: ArrayList<GuideTeachers>): RecyclerView.Adapter<GuideTeachersAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.card_person, parent, false))
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.name.text = "${guideTeachers[position].name} ${guideTeachers[position].lastName}"
        holder.group.text = guideTeachers[position].group.toString()
    }

    override fun getItemCount(): Int = guideTeachers.size

    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val name: TextView = itemView.findViewById(R.id.cardPersonName)
        val group: TextView = itemView.findViewById(R.id.cardPersonPosition)
    }
}