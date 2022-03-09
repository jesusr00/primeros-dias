package com.jesusr00.primarosdas.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.jesusr00.primarosdas.R
import com.jesusr00.primarosdas.models.DirectionCouncilMember

class DirectionCouncilAdapter(private val directionCouncilMember: ArrayList<DirectionCouncilMember>): RecyclerView.Adapter<DirectionCouncilAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.card_person, parent, false))
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.personName.text = "${directionCouncilMember[position].name} ${directionCouncilMember[position].lastName}"
        holder.personPosition.text = directionCouncilMember[position].position
    }

    override fun getItemCount(): Int = directionCouncilMember.size

    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val personName: TextView = itemView.findViewById(R.id.cardPersonName)
        val personPosition: TextView = itemView.findViewById(R.id.cardPersonPosition)
        val personPhoto: ImageView = itemView.findViewById(R.id.cardPersonPhoto)
    }
}