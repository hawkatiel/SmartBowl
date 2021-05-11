package com.example.smartbowlapp.models
import java.io.Serializable

data class DayEntry(
    // Basic Info
    var date: String = "New Entry",

    // Macro info
    var totalCalories: Double = 0.0,
    var totalCarbs: Double = 0.0,
    var totalFats: Double = 0.0,
    var totalProtein: Double = 0.0,

    var mealEntries: MutableList<MealEntry> = mutableListOf<MealEntry>(
        MealEntry("Breakfast"),
        MealEntry("Lunch"),
        MealEntry("Dinner"),
        MealEntry("Snacks")
    )


) : Serializable {
    fun updateAllValues() {
        // Update Meal Totals first
        mealEntries.forEach {
            it.updateMealValues()
        }

        // Then update Day Totals
        totalCalories = mealEntries[0].totalCalories +
                mealEntries[1].totalCalories +
                mealEntries[2].totalCalories +
                mealEntries[3].totalCalories

        totalCarbs = mealEntries[0].totalCarbs +
                mealEntries[1].totalCarbs +
                mealEntries[2].totalCarbs +
                mealEntries[3].totalCarbs

        totalFats = mealEntries[0].totalFats +
                mealEntries[1].totalFats +
                mealEntries[2].totalFats +
                mealEntries[3].totalFats

        totalProtein = mealEntries[0].totalProtein +
                mealEntries[1].totalProtein +
                mealEntries[2].totalProtein +
                mealEntries[3].totalProtein
    }

    operator fun set(mealType: Int, value: MealEntry) {
        mealEntries[mealType] = value
    }
}