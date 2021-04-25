package com.example.smartbowlapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.content.Intent
import android.widget.Button
import kotlinx.android.synthetic.main.activity_foodentry.*

var currentFoodEntry = FoodEntry()

class FoodEntryActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_foodentry)

        // Action Bar Support
        val actionBar = supportActionBar
        actionBar!!.title = "Food Entry"       // Set Action Bar title
        actionBar.setDisplayHomeAsUpEnabled(false)   // Turn on Back Button

        // Loading the food entry!
        // Check if this instance is a new entry or not. If true, go ahead and update the screen with the default values for a new food entry
        var newEntry = intent.getBooleanExtra("EXTRA_NEWENTRY", false)
        if (newEntry) {
            updateFoodEntryActivity()
        } else {    // Otherwise pull data for the associated food entry from files
            //var currentFoodEntry = [figure out how to copy data from files based on the button that was clicked. I guess we're passing something to identify it']
            updateFoodEntryActivity()
        }

        // Camera Button
        btnFoodEntryAutoAdd.setOnClickListener {
            Intent(this, CameraActivity::class.java).also {
                startActivity(it)
            }
        }

        // Save Entry Button
        btnFoodEntrySave.setOnClickListener {
            // Save currentFoodEntry to file???
            // Back out of this activity
        }
    }

    fun updateFoodEntryActivity () {
        etFoodEntryName.setText(currentFoodEntry.foodName)
        tvFoodEntryServingValue.setText(currentFoodEntry.servingSize.toString())

        tvFoodEntryCaloriesValue.setText(currentFoodEntry.totalCalories.toString())
        tvFoodEntryCarbsValue.setText(currentFoodEntry.totalCarbs.toString())
        tvFoodEntryFatsValue.setText(currentFoodEntry.totalFats.toString())
        tvFoodEntryProteinValue.setText(currentFoodEntry.totalProtein.toString())
    }
}

