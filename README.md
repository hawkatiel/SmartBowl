# SmartBowl
man

FILEPATH for all the good stuff

C:\Users\Rachel\AndroidStudioProjects\SmartBowlApp\app\src\main

4-20 :  Added some intents to move between activities
        Added \java
        Added manifest file
        Added build.gradle

4-25 :  UI changes
                All
                        No more back button in the action bar due to crashing the app sometimes for some reason
                Main
                        The background is a slightly deeper green.
                DayEntry
                        Save Button                
        DayEntry & MealEntry class functions
                Changed macronutrient totals to doubles instead of floats to make rounding easier
                Helper function to recalculate macronutrient totals from respective Meal/Food lists
        MainActivity
                Added RecyclerView and it actually works (!!!!)
                Correctly updates when Activity is resumed
                Buttons correctly open/delete associated DayEntryView
                Added some dummy data on Application launch: 3 DayEntry's, all empty except the first one that has mashed potatoes
        DayEntryActivity
                Actually opens the correct DayEntry now
                Buttons that launch MealEntryActivity (don't change colors yet tho)
                Macronutrient values get updated on Activity creation, but not on resuming yet
                Save Button successfully navigates back to Main but doesn't actually save data yet
                
                
