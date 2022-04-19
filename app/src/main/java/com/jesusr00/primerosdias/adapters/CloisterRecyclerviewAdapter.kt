package com.jesusr00.primerosdias.adapters

import androidx.fragment.app.FragmentManager
import com.jesusr00.primerosdias.models.Professor

class CloisterRecyclerviewAdapter(
    private val professors: ArrayList<Professor>,
    fragmentManager: FragmentManager,
): PersonAdapter<Professor>(professors, fragmentManager) {
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        super.onBindViewHolder(holder, position)
        holder.position.text = "${professors[position].teacherType} de ${professors[position].classType}"
        holder.phone.text = "Nombre de usuario: ${professors[position].username}"
    }
}