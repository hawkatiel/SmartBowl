package com.example.smartbowlapp

import kotlinx.android.synthetic.main.activity_mealentry.*
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.content.Intent
import android.widget.Button
import kotlinx.android.synthetic.main.activity_dayentry.*
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.smartbowlapp.R.*

class MealEntryActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layout.activity_mealentry)

        // Action Bar Support
        val actionBar = supportActionBar
        actionBar!!.title = "Meal Entry"       // Set Action Bar title
        actionBar.setDisplayHomeAsUpEnabled(false)   // Turn on Back Button


        // Change colors depending on Meal type
        val mealType = intent.getIntExtra("EXTRA_MEALTYPE", 3)
        when (mealType) {
            0 -> {  // Breakfast colors
                tvMealEntryName.text = "Breakfast"
                tvMealEntryName.setBackgroundResource(R.color.meal_1)
                rvMealEntryFoodEntryList.setBackgroundResource(R.color.meal_1_2)
                btnMealEntryAddFoodEntry.setBackgroundColor(getResources().getColor(R.color.meal_1))
                btnMealEntrySave.setBackgroundColor(getResources().getColor(R.color.meal_1))
            } 1 -> { // Lunch colors
                tvMealEntryName.text = "Lunch"
                tvMealEntryName.setBackgroundResource(R.color.meal_2)
                rvMealEntryFoodEntryList.setBackgroundResource(R.color.meal_2_2)
                btnMealEntryAddFoodEntry.setBackgroundColor(getResources().getColor(R.color.meal_2))
            btnMealEntrySave.setBackgroundColor(getResources().getColor(R.color.meal_2))
            } 2 -> { // Dinner colors
                tvMealEntryName.text = "Dinner"
                tvMealEntryName.setBackgroundResource(R.color.meal_3)
                rvMealEntryFoodEntryList.setBackgroundResource(R.color.meal_3_2)
                btnMealEntryAddFoodEntry.setBackgroundColor(getResources().getColor(R.color.meal_3))
            btnMealEntrySave.setBackgroundColor(getResources().getColor(R.color.meal_3))
            } 3 -> { // Snack colors
                tvMealEntryName.text = "Snacks"
                tvMealEntryName.setBackgroundResource(R.color.meal_4)
                rvMealEntryFoodEntryList.setBackgroundResource(R.color.meal_4_2)
                btnMealEntryAddFoodEntry.setBackgroundColor(getResources().getColor(R.color.meal_4))
            btnMealEntrySave.setBackgroundColor(getResources().getColor(R.color.meal_4))
            }
        }


        // Add new food item! New entry.
        btnMealEntryAddFoodEntry.setOnClickListener {
            // Launch Food Entry Activity with newEntry bool set to true
            val newEntry = true
            Intent(this, FoodEntryActivity::class.java).also {
                it.putExtra("EXTRA_NEWENTRY", newEntry)
                startActivity(it)
            }
        }
    }

    // Need buttons + intents for "Edit" existing food entries
    //
}