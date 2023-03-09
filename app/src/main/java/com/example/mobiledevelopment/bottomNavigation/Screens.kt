package com.example.mobiledevelopment.bottomNavigation

import android.content.Context
import androidx.compose.runtime.Composable
import com.example.mobiledevelopment.screens.FullDayPrint
import com.example.mobiledevelopment.screens.ScreenWeather
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.mobiledevelopment.screens.ScreenFavorite
import com.example.mobiledevelopment.screens.ScreenTodos

@Composable
fun NavGraph(
    navHostController: NavHostController,
    context: Context
) {
    NavHost(navController = navHostController, startDestination = "screen_1"){
        composable("screen_1"){
            ScreenTodos()
        }
        composable("screen_2"){
            FullDayPrint()
        }
        composable("screen_3"){
            ScreenFavorite()
        }
        composable("screen_4"){
            ScreenWeather(context)
        }
    }

}