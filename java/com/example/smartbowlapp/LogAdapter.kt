package com.example.smartbowlapp

import android.text.Layout
import kotlinx.android.synthetic.main.item_dayentry.view.*
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import android.content.Intent
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.content.ContextCompat.startActivity

class DayAdapter (
    // List of the Class item that goes into the RV.
    var dayLog: ArrayList<DayEntry>,
    val onDayEditClickListener: OnDayEditClickListener,
    val onDayDeleteClickListener: OnDayDeleteClickListener

) : RecyclerView.Adapter<DayViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DayViewHolder {
        return DayViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_dayentry, parent, false))
    }


    override fun onBindViewHolder(holder: DayViewHolder, position: Int) {
        val day = dayLog[position]

        // Day Item view adjustments
        holder.itemView.tvDayEntryEdit.text = day.date

        // Setting that onClick listener
        holder.itemView.setOnClickListener{
            onDayEditClickListener.onDayItemClicked(position)
        }

        holder.itemView.btnDayEntryDelete.setOnClickListener {
            onDayDeleteClickListener.onDayItemDeleteClicked(position)
        }
    }

    override fun getItemCount(): Int {
        return dayLog.size
    }

}
