package com.example.smartbowlapp


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.android.synthetic.main.activity_dayentry.*

import android.content.Intent
import android.widget.Button

class DayEntryActivity : AppCompatActivity() {

    var currentDayEntry : DayEntry = DayEntry()
    var currentPosition : Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dayentry)
        Log.d("out", "DayEntryActivity: entered")

        // Action Bar Support
        val actionBar = supportActionBar
        actionBar!!.title = "Day Entry"       // Set Action Bar title
        actionBar.setDisplayHomeAsUpEnabled(false)


        // Loading Day Entry data from the intent
        currentDayEntry = intent.getSerializableExtra("EXTRA_DAY") as DayEntry
        currentPosition = intent.getIntExtra("EXTRA_POSITION", 0) as Int
        currentDayEntry.updateAllValues()
        updateDayEntryActivity()


        // Meal Buttons. Passing the meal type as well.
        btnDayEntryBreakfast.setOnClickListener {
            Intent(this, MealEntryActivity::class.java).also {
                it.putExtra("EXTRA_MEAL", currentDayEntry.mealEntries[0])
                startActivityForResult(it, 0)
            }
        }
        btnDayEntryLunch.setOnClickListener {
            Intent(this, MealEntryActivity::class.java).also {
                it.putExtra("EXTRA_MEAL", currentDayEntry.mealEntries[1])
                startActivityForResult(it, 1)
            }
        }
        btnDayEntryDinner.setOnClickListener {
            Intent(this, MealEntryActivity::class.java).also {
                it.putExtra("EXTRA_MEAL", currentDayEntry.mealEntries[2])
                startActivityForResult(it, 2)
            }
        }
        btnDayEntrySnacks.setOnClickListener {
            Intent(this, MealEntryActivity::class.java).also {
                it.putExtra("EXTRA_MEAL", currentDayEntry.mealEntries[3])
                startActivityForResult(it, 3)
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

    }

    fun updateDayEntryActivity() {
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
    }


    // On return from MealEntryActivity, update the relevant MealEntry.
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
//        Log.d("out", "DayEntryActivity: on activity result")
        if (data != null) {
//            Log.d("out", "DayEntryActivity: data is not null")
            if (data.hasExtra("EXTRA_MEAL")) {
                Log.d("out", "DayEntryActivity: MealEntry successfully saved")
                val resultMealEntry: MealEntry = data.getSerializableExtra("EXTRA_MEAL") as MealEntry
                val mealType = requestCode
                currentDayEntry[mealType] = resultMealEntry
            }
        }
        updateDayEntryActivity()
    }
}