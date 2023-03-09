package com.example.mobiledevelopment.bottomNavigation

import android.annotation.SuppressLint
import android.content.Context
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.navigation.compose.rememberNavController
import com.example.mobiledevelopment.data.WeatherData

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun MainScreen(context: Context) {
    val navController = rememberNavController()
    Scaffold(
        bottomBar = { 
            BottomNavigation(navController = navController)
        }
    ) {
        NavGraph(navHostController = navController, context)
    }
}



