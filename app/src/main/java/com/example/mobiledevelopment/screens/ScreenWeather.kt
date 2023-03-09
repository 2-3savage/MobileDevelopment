package com.example.mobiledevelopment.screens

import android.content.Context
import android.location.Address
import android.location.Geocoder
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.android.volley.Request
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.mobiledevelopment.R
import com.example.mobiledevelopment.data.WeatherData
import com.example.mobiledevelopment.ui.theme.Gray
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.MarkerState
import com.google.maps.android.compose.rememberCameraPositionState
import org.json.JSONArray
import org.json.JSONObject
import java.io.IOException


const val API_KEY = "c1e05328a3c84b68a0b80636232702"
@Composable
fun ScreenWeather(context: Context) {
    Column(modifier = Modifier
        .background(Color.White)
        .padding(10.dp)
        .fillMaxWidth()
        .verticalScroll(rememberScrollState()), horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center) {

        val daysList = remember {
            mutableStateOf(listOf<WeatherData>())
        }
        val currentDay = remember {
            mutableStateOf(
                WeatherData(
                    "","","","","", "","", ""
                )
            )
        }
        val dialogState = remember {
            mutableStateOf(false)
        }
        val city = remember {
            mutableStateOf("Красноярск")
        }

        if (dialogState.value){
            DialogSearch(dialogState, onSubmit = {
                city.value = it
            })
        }

        getData(city.value, context, daysList, currentDay)


        val hoursWeather = getWeatherByHours(currentDay.value.hours)

        Box(){
            Text(text = city.value, style = TextStyle(fontWeight = FontWeight.Normal), fontSize = 25.sp)
        }
        Box(){
            Text(text = currentDay.value.currentTemp, style = TextStyle(fontWeight = FontWeight.Normal), fontSize = 75.sp)
        }
        Box(){
            Text(text = currentDay.value.condition, style = TextStyle(fontWeight = FontWeight.Normal), fontSize = 15.sp)
        }
        Box() {
            Text(text = "Макс.: ${currentDay.value.maxTemp}, мин.: ${currentDay.value.minTemp}")
        }
        Box(contentAlignment = Alignment.CenterStart, modifier = Modifier.fillMaxWidth()){
            IconButton(onClick = { dialogState.value = true }) {
                Icon(
                    painter = painterResource(id = R.drawable.search),
                    contentDescription = "Icon",
                    modifier = Modifier.size(20.dp)
                )
            }

        }
        Card(modifier = Modifier
            .size(350.dp, 150.dp)
            .padding(top = 10.dp), shape = RoundedCornerShape(15.dp), elevation = 5.dp) {
            Column(modifier = Modifier
                .fillMaxSize()
                .padding(top = 10.dp), verticalArrangement = Arrangement.Top, horizontalAlignment = Alignment.CenterHorizontally) {
                Text(text = "ПОГОДА НА СЕГОДНЯ", fontSize = 12.sp)
                Divider(modifier = Modifier
                    .padding(top = 10.dp)
                    .size(300.dp, 1.dp), color = Gray)
                LazyRow(modifier = Modifier
                    .padding(top = 10.dp, start = 20.dp, end = 20.dp)
                    ) {
                    itemsIndexed(hoursWeather) { _, item ->
                        Item(item)
                    }
                }
            }
        }
        Card(modifier = Modifier
            .size(350.dp, 385.dp)
            .padding(top = 10.dp), shape = RoundedCornerShape(15.dp), elevation = 5.dp) {
            Column( horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.padding(top = 10.dp)) {
                Row(verticalAlignment = Alignment.Top, horizontalArrangement = Arrangement.Start) {
                    Icon(
                        painter = painterResource(id = R.drawable.calendar),
                        contentDescription = "Icon",
                        modifier = Modifier.size(20.dp)

                    )
                    Text(text = "ПРОГНОЗ НА 7 ДНЕЙ", modifier = Modifier.padding(start = 5.dp))

                }
                Divider(modifier = Modifier
                    .padding(top = 10.dp)
                    .size(300.dp, 1.dp), color = Gray)
                LazyColumn( horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center){
                    itemsIndexed(daysList.value){
                        _, item ->
                        ItemDay(item)

                    }
                }


            }


        }

        val coder = Geocoder(LocalContext.current)
        var result: MutableList<Address>?
        try {
            result = coder.getFromLocationName(city.value, 1)
        }catch (_: IOException){
            result = coder.getFromLocationName("Krasnoyarsk", 1)
        }

        val lat = remember {
            mutableStateOf(1.35)
        }
        val long = remember {
            mutableStateOf(103.87)
        }

        if (result != null) {
            if (result.size != 0){
                val location = result[0]
                lat.value = location.latitude
                long.value = location.longitude
            }
        }
        val cityPosition = LatLng(lat.value, long.value)


        val cameraPositionState = rememberCameraPositionState {
            position = CameraPosition.fromLatLngZoom(cityPosition, 10f)
        }

        Card(modifier = Modifier
            .size(350.dp, 385.dp)
            .padding(top = 10.dp), shape = RoundedCornerShape(15.dp), elevation = 5.dp) {
            GoogleMap(cameraPositionState = cameraPositionState) {
                Marker(
                    state = MarkerState(position = cityPosition),
                    title = city.value,
                    snippet = "Marker"
                )
            }
        }

        Spacer(modifier = Modifier.padding(top = 50.dp))
    }
}




@Composable
fun Item(item: WeatherData){
    Column(verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.padding(end = 10.dp)) {
        Text(text = item.time.split(":")[0].split(" ")[1])
//        Картинка
        AsyncImage(modifier = Modifier.size(35.dp), model = "https:${item.icon}", contentDescription = "im2")
        Text(text = item.currentTemp)
    }
}

@Preview(showBackground = true)
@Composable
fun ItemDay(item: WeatherData){
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Row(modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.SpaceBetween) {
            Text(text = "${item.time.split("-")[1].split(" ")[0]}.${item.time.split("-")[2].split(" ")[0]}", modifier = Modifier.padding(start = 20.dp))
            AsyncImage(modifier = Modifier.size(35.dp), model = "https:${item.icon}", contentDescription = "im2")
            Box(contentAlignment = Alignment.CenterStart, modifier = Modifier.fillMaxWidth(0.35f)){
                Text(text = "${item.maxTemp} / ${item.minTemp}", modifier = Modifier.padding(end = 20.dp))
            }

        }
        Divider(modifier = Modifier
            .padding(top = 10.dp)
            .size(300.dp, 1.dp), color = Gray)
    }

}
fun getData(city: String, context: Context, daysList: MutableState<List<WeatherData>>, currentDay: MutableState<WeatherData>){
    val url = "https://api.weatherapi.com/v1/forecast.json?key=$API_KEY" +
            "&q=$city" +
            "&days=" +
            "7" +
            "&aqi=no&alerts=no"
    val queue = Volley.newRequestQueue(context)
    val sRequest = StringRequest(
        Request.Method.GET,
        url, { response ->
            val list = getWeatherByDays(response)
            currentDay.value = list[0]
            daysList.value = list
        },
        {

        }

    )
    queue.add(sRequest)
}
private fun getWeatherByHours(hours: String): List<WeatherData>{
    if (hours.isEmpty()) return listOf()
    val hoursArray = JSONArray(hours)
    val list = ArrayList<WeatherData>()
    for (i in 0 until hoursArray.length()){
        val item = hoursArray[i] as JSONObject
        list.add(
            WeatherData(
                "", item.getString("time"), item.getString("temp_c").toFloat().toInt().toString() + "°C",
                item.getJSONObject("condition").getString("text"), item.getJSONObject("condition").getString("icon"),
                "", "", ""
            )
        )
    }
    return list
}

private fun getWeatherByDays(response: String): List<WeatherData>{
    if (response.isEmpty()) return listOf()
    val list = ArrayList<WeatherData>()
    val mainObject = JSONObject(response)
    val city = mainObject.getJSONObject("location").getString("name")
    val days = mainObject.getJSONObject("forecast").getJSONArray("forecastday")
    for (i in 0 until days.length()){
        val item = days[i] as JSONObject
        list.add(
            WeatherData(
                city, item.getString("date"),"", item.getJSONObject("day")
                    .getJSONObject("condition").getString("text"), item.getJSONObject("day")
                    .getJSONObject("condition").getString("icon"), item.getJSONObject("day")
                    .getString("maxtemp_c"), item.getJSONObject("day")
                    .getString("mintemp_c"), item.getJSONArray("hour").toString()
            )
        )
    }
    list[0] = list[0].copy(
        time = mainObject.getJSONObject("current").getString("last_updated"),
        currentTemp = mainObject.getJSONObject("current").getString("temp_c")
    )
    return list
}
@Composable
private fun DialogSearch(dialogState: MutableState<Boolean>, onSubmit: (String) -> Unit){
    val dialogText = remember{
        mutableStateOf("")
    }
    AlertDialog(onDismissRequest = {
        dialogState.value = false
    }, confirmButton = {
        TextButton(onClick = {dialogState.value = false }) {
            onSubmit(dialogText.value)
            Text(text = "ОК")
        }
    }, dismissButton = {
        TextButton(onClick = {dialogState.value = false}) {
            Text(text = "Cancel")
        }
    }, title = {
        Column(modifier = Modifier.fillMaxWidth()) {
            Text(text = "Введите название города:")
            TextField(value = dialogText.value, onValueChange = {
                dialogText.value = it
            })
        }
    }

    )
}




