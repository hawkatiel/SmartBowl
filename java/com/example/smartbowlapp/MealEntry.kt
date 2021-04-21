package com.example.smartbowlapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.content.Intent
import android.widget.Button

class MealEntry : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mealentry)

        // Action Bar Support
        val actionBar = supportActionBar
        actionBar!!.title = "Meal Entry"       // Set Action Bar title
        actionBar.setDisplayHomeAsUpEnabled(true)   // Turn on Back Button
    }
}