package com.example.smartbowlapp

import kotlinx.android.synthetic.main.activity_dayentry.*
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.content.Intent
import android.widget.Button
import kotlin.math.roundToLong

class DayEntryActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dayentry)

        // Action Bar Support
        val actionBar = supportActionBar
        actionBar!!.title = "Day Entry"       // Set Action Bar title
        actionBar.setDisplayHomeAsUpEnabled(false)   // Turn on Back Button


        // Loading Day Entry data from the intent
        var currentDayEntry = intent.getSerializableExtra("EXTRA_DAY") as DayEntry
        val currentPosition = intent.getIntExtra("EXTRA_POSITION", 0) as Int
        currentDayEntry.updateAllValues()
        tvDayEntryDate.setText(currentDayEntry.date)
        tvDayEntryCaloriesValue.text = "%.2f".format(currentDayEntry.totalCalories)
        tvDayEntryCarbsValue.text = "%.2f".format(currentDayEntry.totalCarbs)
        tvDayEntryFatsValue.text = "%.2f".format(currentDayEntry.totalFats)
        tvDayEntryProteinValue.text = "%.2f".format(currentDayEntry.totalProtein)
        tvDayEntryBreakfastCalValue.text = "%.2f".format(currentDayEntry.mealEntries[0].totalCalories)
        tvDayEntryLunchCalValue.text = "%.2f".format(currentDayEntry.mealEntries[1].totalCalories)
        tvDayEntryDinnerCalValue.text = "%.2f".format(currentDayEntry.mealEntries[2].totalCalories)
        tvDayEntrySnacksCalValue.text = "%.2f".format(currentDayEntry.mealEntries[3].totalCalories)

        /*
         */


        // Meal Buttons. Passing the meal type as well.
        btnDayEntryBreakfast.setOnClickListener {
            Intent(this, MealEntryActivity::class.java).also {
                it.putExtra("EXTRA_MEALTYPE", 0)
                startActivity(it)
            }
        }
        btnDayEntryLunch.setOnClickListener {
            Intent(this, MealEntryActivity::class.java).also {
                it.putExtra("EXTRA_MEALTYPE", 1)
                startActivity(it)
            }
        }
        btnDayEntryDinner.setOnClickListener {
            Intent(this, MealEntryActivity::class.java).also {
                it.putExtra("EXTRA_MEALTYPE", 2)
                startActivity(it)
            }
        }
        btnDayEntrySnacks.setOnClickListener {
            Intent(this, MealEntryActivity::class.java).also {
                it.putExtra("EXTRA_MEALTYPE", 3)
                startActivity(it)
            }
        }


        // Save and return to MainActivity.
        btnDayEntrySave.setOnClickListener {
            currentDayEntry.date = tvDayEntryDate.text.toString()
            val data = Intent()
            data.putExtra("EXTRA_DAY", currentDayEntry)
            data.putExtra("EXTRA_POSITION", currentPosition)
            setResult(RESULT_OK, data)
            finish()
        }


        fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
            super.onActivityResult(requestCode, resultCode, data)
            currentDayEntry.date = tvDayEntryDate.text.toString()
            val data = Intent()
            data.putExtra("EXTRA_DAY", currentDayEntry)
            data.putExtra("EXTRA_POSITION", currentPosition)
            setResult(RESULT_OK, data)
            finish()
        }

    }
}