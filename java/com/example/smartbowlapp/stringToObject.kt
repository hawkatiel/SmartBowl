package com.example.smartbowlapp

import com.google.gson.Gson
import com.google.gson.GsonBuilder

fun stringToObject(dataString: String): ArrayList<DayEntry>{
    val gson = Gson()
    var dataLog : ArrayList<DayEntry> = gson.fromJson(dataString, Array<DayEntry>::class.java).toList() as ArrayList<DayEntry>

    return dataLog
}