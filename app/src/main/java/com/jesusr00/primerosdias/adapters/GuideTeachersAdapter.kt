package com.jesusr00.primerosdias.adapters

import androidx.fragment.app.FragmentManager
import com.jesusr00.primerosdias.models.GuideTeachers


class GuideTeachersAdapter(
    private val guideTeachers: ArrayList<GuideTeachers>,
    fragmentManager: FragmentManager
): PersonAdapter<GuideTeachers>(guideTeachers, fragmentManager) {
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        super.onBindViewHolder(holder, position)
        holder.position.text = guideTeachers[position].group.toString()
    }
}