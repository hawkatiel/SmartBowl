package com.example.smartbowlapp

// OG & Layout imports
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*
import android.content.Intent
import android.widget.Button
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager

class MainActivity : AppCompatActivity(), OnDayEditClickListener, OnDayDeleteClickListener {

    var dayLog = ArrayList<DayEntry>()
    var dayAdapter = DayAdapter(dayLog, onDayEditClickListener = this, onDayDeleteClickListener = this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        // Action Bar Support
        val actionBar = supportActionBar
        actionBar!!.title = "Smart Bowl Meal Log"       // Set Action Bar title


        // Test Log Entries
        dayLog.add(DayEntry(date = "4/20/2021"))
        dayLog.add(DayEntry(date = "4/21/2021"))
        dayLog.add(DayEntry(date = "4/22/2021"))
        println("Back to Main.")
        dayLog[0].mealEntries[0].foodEntries.add(FoodEntry(servingSize = 210.0, foodName = "Mashy Potatoes", totalCalories = 237.3654654, totalCarbs = 35.3, totalFats = 8.9, totalProtein = 3.9))


        // Initializing the adapter
        //dayAdapter = DayAdapter(dayLog, this)
        rvDayLog.layoutManager = LinearLayoutManager(this)
        rvDayLog.adapter = dayAdapter
        dayAdapter.notifyDataSetChanged()


        // Add Day Entry Button.
        // Setting the button's onClick listener to create an Intent to open Meal Entry Activity. In the same motion, it executes the Intent.
        val btnMainAddDayEntry = findViewById<Button>(R.id.btnMainAddDayEntry)
        btnMainAddDayEntry.setOnClickListener {
            dayLog.add(DayEntry(date = "4/25/2021"))
            Intent(this, DayEntryActivity::class.java).also {
                it.putExtra("EXTRA_DAY", dayLog[dayLog.size-1])
                startActivity(it)
            }
        }
    }

    override fun onResume() {
        super.onResume()
        dayAdapter.notifyDataSetChanged()
    }


    // Defining function to open Day Entry
    override fun onDayItemClicked(position: Int) {
        val intent = Intent(this, DayEntryActivity::class.java).also {
            it.putExtra("EXTRA_DAY", dayLog[position])
            startActivity(it)
        }
    }

    // Defining function to delete Day Entry
    override fun onDayItemDeleteClicked(position: Int) {
        dayLog.removeAt(position)
        dayAdapter.notifyDataSetChanged()
    }
}