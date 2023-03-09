package com.example.mobiledevelopment.bottomNavigation

import androidx.compose.foundation.background
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState

@Composable
fun BottomNavigation(
    navController: NavController
) {
    val listItems = listOf(
        BottomItem.Screen1, BottomItem.Screen2, BottomItem.Screen3, BottomItem.Screen4
    )
    androidx.compose.material.BottomNavigation(
        backgroundColor = Color.White
    ) {
        val backStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = backStackEntry?.destination?.route // Смотрим, что за окно открыто
        listItems.forEach { bottomItem ->
            BottomNavigationItem(
                selected = currentRoute == bottomItem.route,
                onClick = {
                    navController.navigate(bottomItem.route)

                },
                icon = {
                    Icon(
                        painter = painterResource(id = bottomItem.icon_id),
                        contentDescription = "Icon"
                    )
                }
                ,
                selectedContentColor = Color.Red, unselectedContentColor = Color.Gray
            )
        }
    }
}