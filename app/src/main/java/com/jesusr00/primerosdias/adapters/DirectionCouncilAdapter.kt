package com.jesusr00.primerosdias.adapters

import androidx.fragment.app.FragmentManager
import com.jesusr00.primerosdias.models.DirectionCouncilMember

class DirectionCouncilAdapter(
    private val directionCouncilMember: ArrayList<DirectionCouncilMember>,
    fragmentManager: FragmentManager
):  PersonAdapter<DirectionCouncilMember>(directionCouncilMember, fragmentManager) {
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        super.onBindViewHolder(holder, position)
        holder.position.text = directionCouncilMember[position].position
    }
}