package com.example.smartbowlapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.content.Intent
import android.widget.Button

class DayEntry : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dayentry)

        // Action Bar Support
        val actionBar = supportActionBar
        actionBar!!.title = "Day Entry"       // Set Action Bar title
        actionBar.setDisplayHomeAsUpEnabled(true)   // Turn on Back Button
    }
}