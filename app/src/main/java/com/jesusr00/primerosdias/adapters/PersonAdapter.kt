package com.jesusr00.primerosdias.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.RecyclerView
import com.jesusr00.primerosdias.R
import com.jesusr00.primerosdias.models.Person
import com.jesusr00.primerosdias.ui.fullscreen_image.DialogImageFragment

open class PersonAdapter<T: Person>(
    private val people: ArrayList<T>,
    private val fragmentManager: FragmentManager
): RecyclerView.Adapter<PersonAdapter<T>.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.card_person, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.name.text = "${people[position].name} ${people[position].lastName}"
        holder.photo.apply {
            if (people[position].photo.photo != null && people[position].photo.photo!!.isNotEmpty()){
                setImageBitmap(people[position].photo.photo())
                setOnClickListener {
                    DialogImageFragment.newInstance(people[position].photo.id!!)
                        .show(fragmentManager, "dialog")
                }
            }
            else{
                setImageResource(R.drawable.ic_fte_black_logo)
                setOnClickListener(null)
            }
        }

    }

    override fun getItemCount(): Int = people.size

    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val name: TextView = itemView.findViewById(R.id.cardPersonName)
        val photo: ImageView = itemView.findViewById(R.id.cardPersonPhoto)
        val position: TextView = itemView.findViewById(R.id.cardPersonPosition)
        val phone: TextView = itemView.findViewById(R.id.cardPersonPhone)
    }

}