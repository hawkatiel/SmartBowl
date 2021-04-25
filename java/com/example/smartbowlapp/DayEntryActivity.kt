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
        var currentDate = intent.getSerializableExtra("EXTRA_DAY") as DayEntry
        currentDate.updateAllValues()
        tvDayEntryDate.setText(currentDate.date)
        tvDayEntryCaloriesValue.text = "%.2f".format(currentDate.totalCalories)
        tvDayEntryCarbsValue.text = "%.2f".format(currentDate.totalCarbs)
        tvDayEntryFatsValue.text = "%.2f".format(currentDate.totalFats)
        tvDayEntryProteinValue.text = "%.2f".format(currentDate.totalProtein)
        tvDayEntryBreakfastCalValue.text = "%.2f".format(currentDate.mealEntries[0].totalCalories)
        tvDayEntryLunchCalValue.text = "%.2f".format(currentDate.mealEntries[1].totalCalories)
        tvDayEntryDinnerCalValue.text = "%.2f".format(currentDate.mealEntries[2].totalCalories)
        tvDayEntrySnacksCalValue.text = "%.2f".format(currentDate.mealEntries[3].totalCalories)

        /*
         */


        // Meal Buttons. Passing the meal type as well.
        btnDayEntryBreakfast.setOnClickListener {
            Intent(this, MealEntryActivity::class.java).also {
                it.putExtra("EXTRA_MEALTYPE", 1)
                startActivity(it)
            }
        }
        btnDayEntryLunch.setOnClickListener {
            Intent(this, MealEntryActivity::class.java).also {
                it.putExtra("EXTRA_MEALTYPE", 2)
                startActivity(it)
            }
        }
        btnDayEntryDinner.setOnClickListener {
            Intent(this, MealEntryActivity::class.java).also {
                it.putExtra("EXTRA_MEALTYPE", 3)
                startActivity(it)
            }
        }
        btnDayEntrySnacks.setOnClickListener {
            Intent(this, MealEntryActivity::class.java).also {
                it.putExtra("EXTRA_MEALTYPE", 4)
                startActivity(it)
            }
        }

        // Save
        btnDayEntrySave.setOnClickListener {
            currentDate.date = tvDayEntryDate.text.toString()

            finish()
        }

    }
}