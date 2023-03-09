package com.example.mobiledevelopment.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Constraints
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.mobiledevelopment.note.MainViewModel
import com.example.mobiledevelopment.note.Note
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun NoteScreen(navController: NavController, viewModel: MainViewModel, noteId: String?) {

    val notes = viewModel.readAllNotes().observeAsState(listOf()).value
    val note = notes.firstOrNull{it.id == noteId?.toInt()} ?: Note(title = "none", subtitle = "none")
    val bottomSheetState = rememberModalBottomSheetState(ModalBottomSheetValue.Hidden)
    val coroutineScope = rememberCoroutineScope()
    val title = remember {
        mutableStateOf("")
    }
    val subtitle = remember {
        mutableStateOf("")
    }
    ModalBottomSheetLayout(
        sheetState = bottomSheetState,
        sheetElevation = 5.dp,
        sheetShape = RoundedCornerShape(topStart = 30.dp, topEnd = 30.dp),
        sheetContent = {
            Surface() {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(all = 32.dp)

                ) {
                    Text(
                        text = "Изменить заметку",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(vertical = 8.dp)
                    )
                    OutlinedTextField(
                        value = title.value,
                        onValueChange = { title.value = it },
                        isError = title.value.isEmpty(),
                        label = {
                            Text(
                                text = "Заголовок"
                            )
                        })
                    OutlinedTextField(
                        value = subtitle.value,
                        onValueChange = { subtitle.value = it },
                        isError = subtitle.value.isEmpty(),
                        label = {
                            Text(
                                text = "Заметка"
                            )
                        })
                    Button(onClick = {
                                     viewModel.updateNote( note = Note(id = note.id, title = title.value, subtitle = subtitle.value)){
                                         navController.navigate(NavRoute.Start.route)
                                     }
                    }, modifier = Modifier.padding(top = 16.dp, bottom = 30.dp)) {
                        Text(text = "Обновить")
                    }
                }
            }
        }) {
        Column(modifier = Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally) {
            Row(modifier = Modifier
                .fillMaxWidth()
                .padding(top = 12.dp), verticalAlignment = Alignment.Top, horizontalArrangement = Arrangement.SpaceEvenly) {
                Button(onClick = { navController.navigate(NavRoute.Start.route) }) {
                    Text(text = "Назад")
                }
                Text(text = "Заметка №${note.id}", fontSize = 24.sp)
            }

            Text(text = "Заметка: ${note.title}", fontSize = 24.sp, fontWeight = FontWeight.Bold, modifier = Modifier.padding(top = 48.dp))
            Text(text = note.subtitle, fontSize = 18.sp, fontWeight = FontWeight.Light, modifier = Modifier.padding(top = 96.dp))
            Row(modifier = Modifier
                .fillMaxSize()
                .padding(bottom = 90.dp), verticalAlignment = Alignment.Bottom, horizontalArrangement = Arrangement.SpaceEvenly) {
                Button(onClick = { viewModel.deleteNote(note = note){
                    navController.navigate(NavRoute.Start.route)
                } }) {
                    Text(text = "Удалить заметку")
                }
                Button(onClick = { coroutineScope.launch {
                    title.value = note.title
                    subtitle.value = note.subtitle
                    bottomSheetState.show()
                } }) {
                    Text(text = "Обновить заметку")
                }

            }

        }
    }


}