package com.example.smartbowlapp


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.android.synthetic.main.activity_foodentry.*

import android.content.Intent
import android.widget.Button

class FoodEntryActivity : AppCompatActivity() {

    var currentFoodEntry : FoodEntry = FoodEntry()
    var currentPosition : Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_foodentry)
        Log.d("out", "FoodEntry entered")

        // Action Bar Support
        val actionBar = supportActionBar
        actionBar!!.title = "Food Entry"       // Set Action Bar title
        actionBar.setDisplayHomeAsUpEnabled(false)


        // Loading the food entry!
        currentFoodEntry = intent.getSerializableExtra("EXTRA_FOOD") as FoodEntry
        currentPosition = intent.getIntExtra("EXTRA_POSITION", 0) as Int
        updateFoodEntryActivity()



        // Camera Button
        btnFoodEntryAutoAdd.setOnClickListener {
            Intent(this, CameraActivity::class.java).also {
                startActivityForResult(it,1)
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

