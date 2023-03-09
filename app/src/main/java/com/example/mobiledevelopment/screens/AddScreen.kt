package com.example.mobiledevelopment.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.mobiledevelopment.note.MainViewModel
import com.example.mobiledevelopment.note.Note

@Preview(showBackground = true)
@Composable
fun AddScreen(navController: NavController, viewModel: MainViewModel) {
    val title = remember { mutableStateOf("") }
    val subtitle = remember {
        mutableStateOf("")
    }
    val isButtonEnabled = remember {
        mutableStateOf(false)
    }
    Column(modifier = Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center) {
        Text(text = "Добавить новую заметку", fontSize = 18.sp, fontWeight = FontWeight.Bold, modifier = Modifier.padding(vertical = 8.dp))

        OutlinedTextField(
            value = title.value,
            onValueChange = {
                title.value = it
                isButtonEnabled.value = title.value.isNotEmpty() && subtitle.value.isNotEmpty()
                            },
            label = { Text(text = "Заголовок")},
            isError = title.value.isEmpty()
            )
        OutlinedTextField(
            value = subtitle.value,
            onValueChange = {
                subtitle.value = it
                isButtonEnabled.value = title.value.isNotEmpty() && subtitle.value.isNotEmpty()
                            },
            label = { Text(text = "Заметка")},
            isError = subtitle.value.isEmpty())
        Button(modifier = Modifier.padding(top = 16.dp), enabled = isButtonEnabled.value, onClick = {
            viewModel.addNote(note = Note(title = title.value, subtitle = subtitle.value)) {
                navController.navigate(NavRoute.Start.route)
            }
        }) {
            Text(text = "Создать")
        }
    }
}