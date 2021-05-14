package com.example.technpractiseandroid.some

import androidx.annotation.DrawableRes
import com.example.technpractiseandroid.R

enum class AppBarItem (
    @DrawableRes val iconSelected: Int) {
    HOME(R.drawable.home_black_24dp),
    TASKS(R.drawable.edit_calendar_black_24dp),
    CREATE(R.drawable.add_circle_outline_black_36dp),
    CABINET(R.drawable.account_circle_black_24dp)
}