package com.jesusr00.primerosdias.adapters

import android.annotation.SuppressLint
import android.content.Context
import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.core.graphics.drawable.toIcon
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.RecyclerView
import com.jesusr00.primerosdias.R
import com.jesusr00.primerosdias.models.DirectionCouncilMember
import com.jesusr00.primerosdias.ui.fullscreen_image.DialogImageFragment

class DirectionCouncilAdapter(
    private val directionCouncilMember: ArrayList<DirectionCouncilMember>,
    private val fragmentManager: FragmentManager
): RecyclerView.Adapter<DirectionCouncilAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.card_person, parent, false))
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.personName.text = "${directionCouncilMember[position].name} ${directionCouncilMember[position].lastName}"
        holder.personPosition.text = directionCouncilMember[position].position
        if (directionCouncilMember[position].photo.photo != null && directionCouncilMember[position].photo.photo!!.isNotEmpty())
            holder.personPhoto.setImageBitmap(directionCouncilMember[position].photo.getPhoto())

        if (directionCouncilMember[position].photo.photo != null && directionCouncilMember[position].photo.photo!!.isNotEmpty())
            holder.personPhoto.setOnClickListener {
                DialogImageFragment.newInstance(directionCouncilMember[position].photo.id!!)
                    .show(fragmentManager, "dialog")
            }
    }

    override fun getItemCount(): Int = directionCouncilMember.size

    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val personName: TextView = itemView.findViewById(R.id.cardPersonName)
        val personPosition: TextView = itemView.findViewById(R.id.cardPersonPosition)
        val personPhoto: ImageView = itemView.findViewById(R.id.cardPersonPhoto)
    }
}