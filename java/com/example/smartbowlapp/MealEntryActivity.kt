package com.example.smartbowlapp

// Layout imports and things
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.android.synthetic.main.activity_mealentry.*

import android.content.Intent
import android.widget.Button

import androidx.recyclerview.widget.LinearLayoutManager
import com.example.smartbowlapp.R.*

class MealEntryActivity : AppCompatActivity(), OnFoodEditClickListener, OnFoodDeleteClickListener  {

    var foodLog = ArrayList<FoodEntry>()
    var foodAdapter = FoodAdapter(foodLog, onFoodEditClickListener = this, onFoodDeleteClickListener = this)

    var currentMeal = MealEntry()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layout.activity_mealentry)
        Log.d("out", "MealEntryActivity: entered")

        // Action Bar Support
        val actionBar = supportActionBar
        actionBar!!.title = "Meal Entry"       // Set Action Bar title
        actionBar.setDisplayHomeAsUpEnabled(false)


        // Change colors depending on Meal type
        currentMeal = intent.getSerializableExtra("EXTRA_MEAL") as MealEntry
        when (currentMeal.mealName) {
            "Breakfast" -> {  // Breakfast colors
                tvMealEntryName.text = "Breakfast"
                tvMealEntryName.setBackgroundResource(R.color.meal_1)
                rvMealEntryFoodEntryList.setBackgroundResource(R.color.meal_1_2)
                btnMealEntryAddFoodEntry.setBackgroundColor(getResources().getColor(R.color.meal_1))
                btnMealEntrySave.setBackgroundColor(getResources().getColor(R.color.meal_1))
            } "Lunch" -> { // Lunch colors
                tvMealEntryName.text = "Lunch"
                tvMealEntryName.setBackgroundResource(R.color.meal_2)
                rvMealEntryFoodEntryList.setBackgroundResource(R.color.meal_2_2)
                btnMealEntryAddFoodEntry.setBackgroundColor(getResources().getColor(R.color.meal_2))
                btnMealEntrySave.setBackgroundColor(getResources().getColor(R.color.meal_2))
            } "Dinner" -> { // Dinner colors
                tvMealEntryName.text = "Dinner"
                tvMealEntryName.setBackgroundResource(R.color.meal_3)
                rvMealEntryFoodEntryList.setBackgroundResource(R.color.meal_3_2)
                btnMealEntryAddFoodEntry.setBackgroundColor(getResources().getColor(R.color.meal_3))
                btnMealEntrySave.setBackgroundColor(getResources().getColor(R.color.meal_3))
            } "Snacks" -> { // Snack colors
                tvMealEntryName.text = "Snacks"
                tvMealEntryName.setBackgroundResource(R.color.meal_4)
                rvMealEntryFoodEntryList.setBackgroundResource(R.color.meal_4_2)
                btnMealEntryAddFoodEntry.setBackgroundColor(getResources().getColor(R.color.meal_4))
                btnMealEntrySave.setBackgroundColor(getResources().getColor(R.color.meal_4))
            }
        }

        // Initializing the adapter
        foodAdapter.foodLog = currentMeal.foodEntries as ArrayList<FoodEntry>

        rvMealEntryFoodEntryList.layoutManager = LinearLayoutManager(this)
        rvMealEntryFoodEntryList.adapter = foodAdapter
        foodAdapter.notifyDataSetChanged()
//        Log.d("out", foodAdapter.itemCount.toString() + " items in the food adapter")
//        Log.d("out", foodAdapter.foodLog.size.toString() + " items in the food adapter's food list")

        updateMealEntryActivity()

        // Add new food item! New entry.
        val btnMealEntryAddFoodEntry = findViewById<Button>(R.id.btnMealEntryAddFoodEntry)
        btnMealEntryAddFoodEntry.setOnClickListener {
            foodAdapter.foodLog.add(FoodEntry())
            Intent(this, FoodEntryActivity::class.java).also {
                it.putExtra("EXTRA_FOOD", foodAdapter.foodLog[foodAdapter.foodLog.size-1])
                it.putExtra("EXTRA_POSITION", foodAdapter.foodLog.size-1)
                startActivityForResult(it,1)
            }
        }

        // Save button (and go back to DayEntry)
        val btnMealEntrySave = findViewById<Button>(R.id.btnMealEntrySave)
        btnMealEntrySave.setOnClickListener {
            updateMealEntryActivity()   // Unnecessary in theory, but just in case...
            val data = Intent()
            data.putExtra("EXTRA_MEAL", currentMeal)
            setResult(RESULT_OK, data)
            Log.d("out", "MealEntryActivity: Saving meal entry...")
            finish()
        }
    }

    override fun onResume() {
        super.onResume()
        foodAdapter.notifyDataSetChanged()
        updateMealEntryActivity()
    }


    // Edit and Delete buttons for FoodLog adapter
    override fun onFoodItemClicked(position: Int) {
        val intent = Intent(this, FoodEntryActivity::class.java).also {
            it.putExtra("EXTRA_FOOD", foodAdapter.foodLog[position])
            it.putExtra("EXTRA_POSITION", position)
            startActivityForResult(it,1)
        }
    }
    override fun onFoodItemDeleteClicked(position: Int) {
        foodAdapter.foodLog.removeAt(position)
        foodAdapter.notifyDataSetChanged()
        updateMealEntryActivity()
    }


    // On return from FoodEntryActivity, update the relevant FoodEntry.
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (data != null) {
            if (data.hasExtra("EXTRA_FOOD")) {
                Log.d("out", "MealEntryActivity: new food added to food list")
                val resultFoodEntry : FoodEntry = data.getSerializableExtra("EXTRA_FOOD") as FoodEntry
                val position : Int = data.getIntExtra("EXTRA_POSITION", foodAdapter.foodLog.size-1) as Int
                foodAdapter.foodLog[position] = resultFoodEntry
            }
            foodAdapter.notifyDataSetChanged()
        }
        updateMealEntryActivity()
    }

    fun updateMealEntryActivity() {
        currentMeal.foodEntries = foodAdapter.foodLog
        currentMeal.updateMealValues()
        tvMealEntryCaloriesValue.text = "%.2f".format(currentMeal.totalCalories)
        tvMealEntryCarbsValue.text = "%.2f".format(currentMeal.totalCarbs)
        tvMealEntryFatsValue.text = "%.2f".format(currentMeal.totalFats)
        tvMealEntryProteinValue.text = "%.2f".format(currentMeal.totalProtein)
    }

}