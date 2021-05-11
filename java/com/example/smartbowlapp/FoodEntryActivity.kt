package com.example.smartbowlapp


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.android.synthetic.main.activity_foodentry.*

import android.content.Intent
import com.example.smartbowlapp.models.FoodEntry

class FoodEntryActivity : AppCompatActivity() {

    var currentFoodEntry : FoodEntry = FoodEntry()
    var currentPosition : Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_foodentry)
        Log.d("out", "FoodEntryActivity: entered")

        // Action Bar Support
        val actionBar = supportActionBar
        actionBar!!.title = "Food Entry"       // Set Action Bar title
        actionBar.setDisplayHomeAsUpEnabled(false)


        // Loading the food entry!
        currentFoodEntry = intent.getSerializableExtra("EXTRA_FOOD") as FoodEntry
        currentPosition = intent.getIntExtra("EXTRA_POSITION", 0) as Int
        updateFoodEntryActivityFields()



        // Camera Button
        btnFoodEntryAutoAdd.setOnClickListener {
            Intent(this, CameraActivity::class.java).also {
                startActivityForResult(it,1)
            }
        }

        // Save Entry Button
        btnFoodEntrySave.setOnClickListener {
            updateFoodEntryData()
            val data = Intent()
            data.putExtra("EXTRA_FOOD", currentFoodEntry)
            data.putExtra("EXTRA_POSITION", currentPosition)
            setResult(RESULT_OK, data)
            finish()
        }
    }

    fun updateFoodEntryActivityFields () {
        etFoodEntryName.setText(currentFoodEntry.foodName)
        tvFoodEntryServingValue.setText(currentFoodEntry.servingSize.toString())

        tvFoodEntryCaloriesValue.setText(currentFoodEntry.totalCalories.toString())
        tvFoodEntryCarbsValue.setText(currentFoodEntry.totalCarbs.toString())
        tvFoodEntryFatsValue.setText(currentFoodEntry.totalFats.toString())
        tvFoodEntryProteinValue.setText(currentFoodEntry.totalProtein.toString())
    }

    fun updateFoodEntryData () {
        currentFoodEntry.foodName = etFoodEntryName.text.toString()
        try {
            currentFoodEntry.servingSize = tvFoodEntryServingValue.text.toString().toDouble()
        } finally {}
        try {
            currentFoodEntry.totalCalories = tvFoodEntryCaloriesValue.text.toString().toDouble()
        } finally {}
        try {
            currentFoodEntry.totalCarbs = tvFoodEntryCarbsValue.text.toString().toDouble()
        } finally {}
        try {
            currentFoodEntry.totalFats = tvFoodEntryFatsValue.text.toString().toDouble()
        } finally {}
        try {
            currentFoodEntry.totalProtein = tvFoodEntryProteinValue.text.toString().toDouble()
        } finally {}
    }


}

