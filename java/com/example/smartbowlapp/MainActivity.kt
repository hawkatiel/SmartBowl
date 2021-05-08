package com.example.smartbowlapp

// OG & Layout imports
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.android.synthetic.main.activity_main.*

import android.content.Intent
import android.widget.Button

import androidx.recyclerview.widget.LinearLayoutManager

// File W/R imports
import android.content.Context
import java.io.*


class MainActivity : AppCompatActivity(), OnDayEditClickListener, OnDayDeleteClickListener {

    var dayLog = ArrayList<DayEntry>()
    var dayAdapter = DayAdapter(dayLog, onDayEditClickListener = this, onDayDeleteClickListener = this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Log.d("out", "MainActivity entered")

        // Action Bar Support
        val actionBar = supportActionBar
        actionBar!!.title = "Smart Bowl Meal Log"       // Set Action Bar title
        actionBar.setDisplayHomeAsUpEnabled(false)


        // Test Log Entries
        dayLog.add(DayEntry(date = "4/20/2021"))
        dayLog.add(DayEntry(date = "4/21/2021"))
        dayLog.add(DayEntry(date = "4/22/2021"))
        dayLog[0].mealEntries[0].foodEntries.add(FoodEntry(servingSize = 210.0, foodName = "Mashy Potatoes", totalCalories = 237.3654654, totalCarbs = 35.3, totalFats = 8.9, totalProtein = 3.9))


        // Initializing the adapter
        rvDayLog.layoutManager = LinearLayoutManager(this)
        rvDayLog.adapter = dayAdapter
        dayAdapter.notifyDataSetChanged()
        Log.d("out", dayLog.size.toString() + " items in the day list")
        Log.d("out", dayAdapter.itemCount.toString() + " items in the day adapter")

        // AddDayEntry Button.
        // Setting the button's onClick listener to create an Intent to open Meal Entry Activity. In the same motion, it executes the Intent.
        val btnMainAddDayEntry = findViewById<Button>(R.id.btnMainAddDayEntry)
        btnMainAddDayEntry.setOnClickListener {
            dayLog.add(DayEntry(date = "4/20/2021"))
            Intent(this, DayEntryActivity::class.java).also {
                it.putExtra("EXTRA_DAY", dayLog[dayLog.size-1])
                it.putExtra("EXTRA_POSITION", dayLog.size-1)
                startActivityForResult(it,1)
            }
        }
    }

    // Update the adapter on MainActivity resume (aka when the back button on DayEntryActivity is pressed, versus using the save button)
    override fun onResume() {
        super.onResume()
        dayAdapter.notifyDataSetChanged()
    }


    // Edit and Delete buttons for DayLog adapter
    override fun onDayItemClicked(position: Int) {
        val intent = Intent(this, DayEntryActivity::class.java).also {
            it.putExtra("EXTRA_DAY", dayLog[position])
            it.putExtra("EXTRA_POSITION", position)
            startActivityForResult(it,1)
        }
    }
    override fun onDayItemDeleteClicked(position: Int) {
        dayLog.removeAt(position)
        dayAdapter.notifyDataSetChanged()
    }


    // On return to main screen from DayEntryActivity, update the appropriate DayEntry within the dayLog and notify adapter.
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (data != null) {
            if (data.hasExtra("EXTRA_DAY")) {
                Log.d("out", "asdf")
                val resultDayEntry : DayEntry = data.getSerializableExtra("EXTRA_DAY") as DayEntry
                val position : Int = data.getIntExtra("EXTRA_POSITION", dayLog.size-1) as Int
                dayLog[position] = resultDayEntry
            }
            dayAdapter.notifyDataSetChanged()
        }
    }
}