package com.jesusr00.primerosdias.adapters

import android.annotation.SuppressLint
import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.core.graphics.drawable.toIcon
import androidx.recyclerview.widget.RecyclerView
import com.jesusr00.primerosdias.R
import com.jesusr00.primerosdias.models.GuideTeachers

class GuideTeachersAdapter(private val guideTeachers: ArrayList<GuideTeachers>): RecyclerView.Adapter<GuideTeachersAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.card_person, parent, false))
    }

    @RequiresApi(Build.VERSION_CODES.M)
    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.name.text = "${guideTeachers[position].name} ${guideTeachers[position].lastName}"
        holder.group.text = guideTeachers[position].group.toString()
        if (guideTeachers[position].photo != null && guideTeachers[position].photo!!.isNotEmpty())
            holder.photo.setImageIcon(guideTeachers[position].photo?.toIcon())
    }

    override fun getItemCount(): Int = guideTeachers.size

    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val name: TextView = itemView.findViewById(R.id.cardPersonName)
        val group: TextView = itemView.findViewById(R.id.cardPersonPosition)
        val photo: ImageView = itemView.findViewById(R.id.cardPersonPhoto)
    }
}