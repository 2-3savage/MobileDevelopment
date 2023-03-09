package com.example.mobiledevelopment.screens

import android.annotation.SuppressLint
import android.app.Application
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.mobiledevelopment.note.MainViewModel
import com.example.mobiledevelopment.note.MainViewModelFactory


sealed class NavRoute(val route: String){
    object Start: NavRoute("start_screen")
    object Main: NavRoute("main_screen")
    object Add: NavRoute("add_screen")
    object Note: NavRoute("note_screen")
}
@Composable
fun NotesNavHost(navHostController: NavHostController, mViewModel : MainViewModel){
    NavHost(navController = navHostController, startDestination = "start_screen"){
        composable(NavRoute.Start.route){ StartScreen(navHostController, viewModel = mViewModel) }
        composable(NavRoute.Main.route){ MainScreen(navHostController) }
        composable(NavRoute.Add.route){ AddScreen(navHostController, viewModel = mViewModel) }
        composable(NavRoute.Note.route + "/{id}"){
            backStackEntry ->
            NoteScreen(navHostController, viewModel = mViewModel, noteId = backStackEntry.arguments?.getString("id")) }

    }
}

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Preview(showBackground = true)
@Composable
fun ScreenTodos() {
    val navController = rememberNavController()
    val context = LocalContext.current
    val mViewModel : MainViewModel = viewModel(factory = MainViewModelFactory(context.applicationContext as Application))
    Scaffold(modifier = Modifier.fillMaxSize()) {
        NotesNavHost(navHostController = navController, mViewModel)
    }
}
