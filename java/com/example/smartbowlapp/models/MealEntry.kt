package com.example.smartbowlapp

import java.io.Serializable

data class MealEntry(
    // Basic Info
    var mealName: String = "Enter food",

    // Macro info
    var totalCalories: Double = 0.0,
    var totalCarbs: Double = 0.0,
    var totalFats: Double = 0.0,
    var totalProtein: Double = 0.0,

    // Food list info
    var foodEntries: MutableList<FoodEntry> = mutableListOf<FoodEntry>()
) : Serializable {

    fun updateMealValues() {
        totalCalories = 0.0
        totalCarbs = 0.0
        totalFats = 0.0
        totalProtein = 0.0
        foodEntries.forEach {
            totalCalories += it.totalCalories
            totalCarbs += it.totalCarbs
            totalFats += it.totalFats
            totalProtein += it.totalProtein
        }

    }
}
