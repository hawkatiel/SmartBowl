package com.example.smartbowlapp

import kotlinx.android.synthetic.main.activity_mealentry.*
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.content.Intent
import android.widget.Button
import kotlinx.android.synthetic.main.activity_dayentry.*
import androidx.recyclerview.widget.LinearLayoutManager

class MealEntryActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mealentry)

        // Action Bar Support
        val actionBar = supportActionBar
        actionBar!!.title = "Meal Entry"       // Set Action Bar title
        actionBar.setDisplayHomeAsUpEnabled(false)   // Turn on Back Button

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