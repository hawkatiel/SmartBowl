package com.example.smartbowlapp.models

import java.io.Serializable

class FoodEntry(
    // Basic Info
    var foodName: String = "",
    var servingSize: Double = 0.0,

    // Macro info
    var totalCalories: Double = 0.0,
    var totalCarbs: Double = 0.0,
    var totalFats: Double = 0.0,
    var totalProtein: Double = 0.0,

) : Serializable {
}