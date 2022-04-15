package com.jesusr00.primerosdias.adapters

import android.annotation.SuppressLint
import androidx.fragment.app.FragmentManager
import com.jesusr00.primerosdias.models.FeuSecretariatMember

class FeuSecretariatAdapter(
    private val feuSecretariatMembers: ArrayList<FeuSecretariatMember>,
    fragmentManager: FragmentManager
): PersonAdapter<FeuSecretariatMember>(feuSecretariatMembers, fragmentManager) {
    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        super.onBindViewHolder(holder, position)
        holder.position.text = feuSecretariatMembers[position].charge
        holder.phone.text = "+53 ${feuSecretariatMembers[position].phoneNumber}"
        holder.photo.setOnClickListener(null)
    }
}