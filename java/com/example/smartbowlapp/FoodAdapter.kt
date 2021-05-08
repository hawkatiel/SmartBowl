package com.example.smartbowlapp

import android.text.Layout
import kotlinx.android.synthetic.main.item_foodentry.view.*
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import android.content.Intent
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.content.ContextCompat.startActivity

class FoodAdapter (

    var foodLog: ArrayList<FoodEntry>,
    val onFoodEditClickListener: OnFoodEditClickListener,
    val onFoodDeleteClickListener: OnFoodDeleteClickListener

) : RecyclerView.Adapter<FoodViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FoodViewHolder {
        return FoodViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_foodentry, parent, false))
    }


    override fun onBindViewHolder(holder: FoodViewHolder, position: Int) {
        val food = foodLog[position]

        // Item view adjustments
        holder.itemView.tvFoodEntryEdit.text = food.foodName

        // Setting that onClick listener
        holder.itemView.setOnClickListener{
            onFoodEditClickListener.onFoodItemClicked(position)
        }

        holder.itemView.btnFoodEntryDelete.setOnClickListener {
            onFoodDeleteClickListener.onFoodItemDeleteClicked(position)
        }
    }

    override fun getItemCount(): Int {
        return foodLog.size
    }

}