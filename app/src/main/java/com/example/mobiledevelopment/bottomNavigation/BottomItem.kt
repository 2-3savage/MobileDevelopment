package com.example.mobiledevelopment.bottomNavigation

import com.example.mobiledevelopment.R

sealed class BottomItem(val icon_id: Int, val route: String){
    object Screen1: BottomItem(R.drawable.calendar, "screen_1")
    object Screen2: BottomItem(R.drawable.event_available, "screen_2")
    object Screen3: BottomItem(R.drawable.favorite, "screen_3")
    object Screen4: BottomItem(R.drawable.snow, "screen_4")
}
