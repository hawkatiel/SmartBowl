package com.example.smartbowlapp

import com.google.gson.Gson
import com.google.gson.GsonBuilder

fun objectToString(dayLog : ArrayList<DayEntry>) : String {
    val gson = Gson()
    var jsonString: String = gson.toJson(dayLog)

    return jsonString
}