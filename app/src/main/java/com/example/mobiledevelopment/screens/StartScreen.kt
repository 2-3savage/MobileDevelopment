package com.example.mobiledevelopment.screens

import android.annotation.SuppressLint
import android.app.Application
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.example.mobiledevelopment.note.MainViewModel
import com.example.mobiledevelopment.note.MainViewModelFactory
import com.example.mobiledevelopment.note.Note
import com.example.mobiledevelopment.utils.TYPE_ROOM

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun StartScreen(navController: NavController, viewModel: MainViewModel) {
    viewModel.initDatabaseStart(TYPE_ROOM)

    val notes = viewModel.readAllNotes().observeAsState(listOf()).value

    Column(modifier = Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally) {
        Text(
            text = "Ваши заметки",
            modifier = Modifier.padding(5.dp)
        )
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceAround) {
            Button(onClick = { viewModel.initDatabase(TYPE_ROOM) {
                    navController.navigate(route = NavRoute.Add.route)
                }
                 }, modifier = Modifier.width(175.dp)) {
                Text(text = "Добавить заметку", style = TextStyle(fontSize = 13.sp))
            }
            Button(onClick = { viewModel.deleteAll() }, modifier = Modifier.width(175.dp)) {
                Text(text = "Убрать все заметки", style = TextStyle(fontSize = 13.sp))
            }

        }

        Divider()
//        Заметки сюда пихать
        LazyColumn{
            items(notes){
                note -> 
                ItemNote(note = note, navController)
            }
        }
        Box(contentAlignment = Alignment.BottomCenter, modifier = Modifier.padding(top = 50.dp)) {
            Spacer(modifier = Modifier.padding(top = 0.dp))
        }

    }
}

@Composable
fun ItemNote(note: Note, navController: NavController){
    Card(modifier = Modifier
        .fillMaxWidth()
        .padding(vertical = 8.dp, horizontal = 24.dp)
        .padding(top = 5.dp), elevation = 6.dp) {
        Column(verticalArrangement = Arrangement.SpaceAround, horizontalAlignment = Alignment.CenterHorizontally) {
            Text(text = note.title, style = TextStyle(fontSize = 24.sp, fontWeight = FontWeight.Bold), modifier = Modifier.padding(bottom = 5.dp))
            Text(text = note.subtitle, modifier = Modifier.padding(bottom = 5.dp))
            Button(onClick = { navController.navigate(route = NavRoute.Note.route + "/${note.id}" ) }, modifier = Modifier.width(155.dp)) {
                Text(text = "Доп.инф.", style = TextStyle(fontSize = 13.sp))
            }
        }

    }

}