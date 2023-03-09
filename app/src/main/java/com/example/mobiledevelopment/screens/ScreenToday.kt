package com.example.mobiledevelopment.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.mobiledevelopment.R
import com.example.mobiledevelopment.ui.theme.Orange
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.pagerTabIndicatorOffset
import com.google.accompanist.pager.rememberPagerState
import kotlinx.coroutines.launch

@Composable
fun FullDayPrint() {
    Column() {
        Group("КИ20-16/2Б (1 Подгруппа)")
//        Кнопки
        TabLayout()



    }
}

@Composable
fun Day(day: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth().padding(5.dp)
            ,
        horizontalArrangement = Arrangement.Center, verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = day, style = TextStyle(
                color = Orange,
                fontWeight = FontWeight.Bold,
            )
        )

    }

}

@OptIn(ExperimentalPagerApi::class)
@Composable
fun TabLayout() {


    val tabList = listOf("Сегодня", "Завтра")
    val pagerState = rememberPagerState()
    val tabIndex = pagerState.currentPage
    val coroutineScope = rememberCoroutineScope()

    Column(
        modifier = Modifier
            .padding(5.dp)
            .clip(RoundedCornerShape(10.dp))
    ) {
        TabRow(selectedTabIndex = tabIndex,
            indicator = { pos ->
                TabRowDefaults.Indicator(Modifier.pagerTabIndicatorOffset(pagerState, pos))
            }, backgroundColor = Color.White
        ) {
            tabList.forEachIndexed { index, s ->
                Tab(selected = false, onClick = {
                    coroutineScope.launch {
                        pagerState.animateScrollToPage(index)
                    }
                }, text = { Text(text = s, style = TextStyle(fontWeight = FontWeight.Bold)) })
            }
        }
        HorizontalPager(
            count = tabList.size, state = pagerState, modifier = Modifier.weight(1.0f)
        ) { index ->
            val list = when(index){
                0 -> Column(modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState())) {
                    //        Сюда надо будет день недели
                    Divider(color = Color.Gray, thickness = 1.dp)
                    Day(day = "Понедельник, 13 февраля")
                    Divider(color = Color.Gray, thickness = 1.dp)
                    Item(
                        time_start = "8:30",
                        time_end = "10:05",
                        location = "Корпус №15, ауд. 5-12, ЭИОС",
                        lesson = "ПРОФЕССИОНАЛЬНО-ОРИЕНТИРОВАННЫЙ ИНОСТРАННЫЙ ЯЗЫК",
                        teacher = "Ямских Т.Н."
                    )
                    Item(
                        time_start = "10:15",
                        time_end = "11:50",
                        location = "Корпус №17, ауд. 3-12",
                        lesson = "РАЗРАБОТКА МОБИЛЬНЫХ ПРИЛОЖЕНИЙ",
                        teacher = "Кузнецов А.С."
                    )
                    Item(
                        time_start = "12:00",
                        time_end = "13:35",
                        location = "Корпус №17, ауд. 3-12",
                        lesson = "МАШИННО_ЗАВИСИМЫЕ ЯЗЫКИ ПРОГРАММИРОВАНИЯ",
                        teacher = "Сарамуд М.В."
                    )
                    Item(
                        time_start = "14:10",
                        time_end = "15:45",
                        location = "Корпус №17, ауд. 3-24",
                        lesson = "ФИЗИЧЕСКАЯ КУЛЬТУРА И СПОРТ",
                        teacher = "Брюханова Н.А."
                    )
                    Item(
                        time_start = "15:55",
                        time_end = "17:30",
                        location = "Корпус №17, ауд. 3-24",
                        lesson = "ФИЗИЧЕСКАЯ КУЛЬТУРА И СПОРТ",
                        teacher = "Брюханова Н.А."
                    )
                    Item(
                        time_start = "17:40",
                        time_end = "19:15",
                        location = "Корпус №17, ауд. 3-24",
                        lesson = "ФИЗИЧЕСКАЯ КУЛЬТУРА И СПОРТ",
                        teacher = "Брюханова Н.А."
                    )
                    Spacer(modifier = Modifier.padding(top = 50.dp))
                }
                1 -> Column(modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState())) {
                    //        Сюда надо будет день недели
                    Divider(color = Color.Gray, thickness = 1.dp)
                    Day(day = "Понедельник, 14 февраля")
                    Divider(color = Color.Gray, thickness = 1.dp)

                    Item(
                        time_start = "8:30",
                        time_end = "10:05",
                        location = "Корпус №15, ауд. 5-12, ЭИОС",
                        lesson = "ПРОФЕССИОНАЛЬНО-ОРИЕНТИРОВАННЫЙ ИНОСТРАННЫЙ ЯЗЫК",
                        teacher = "Ямских Т.Н."
                    )
                    Item(
                        time_start = "10:15",
                        time_end = "11:50",
                        location = "Корпус №17, ауд. 3-12",
                        lesson = "РАЗРАБОТКА МОБИЛЬНЫХ ПРИЛОЖЕНИЙ",
                        teacher = "Кузнецов А.С."
                    )
                    Item(
                        time_start = "12:00",
                        time_end = "13:35",
                        location = "Корпус №17, ауд. 3-12",
                        lesson = "МАШИННО_ЗАВИСИМЫЕ ЯЗЫКИ ПРОГРАММИРОВАНИЯ",
                        teacher = "Сарамуд М.В."
                    )
                    Item(
                        time_start = "14:10",
                        time_end = "15:45",
                        location = "Корпус №17, ауд. 3-24",
                        lesson = "ФИЗИЧЕСКАЯ КУЛЬТУРА И СПОРТ",
                        teacher = "Брюханова Н.А."
                    )
                    Item(
                        time_start = "15:55",
                        time_end = "17:30",
                        location = "Корпус №17, ауд. 3-24",
                        lesson = "ФИЗИЧЕСКАЯ КУЛЬТУРА И СПОРТ",
                        teacher = "Брюханова Н.А."
                    )
                    Item(
                        time_start = "17:40",
                        time_end = "19:15",
                        location = "Корпус №17, ауд. 3-24",
                        lesson = "ФИЗИЧЕСКАЯ КУЛЬТУРА И СПОРТ",
                        teacher = "Брюханова Н.А."
                    )
                    Spacer(modifier = Modifier.padding(top = 50.dp))
                }
                else -> {
                    Column(modifier = Modifier
                        .fillMaxSize()
                        .verticalScroll(rememberScrollState())) {
                        //        Сюда надо будет день недели
                        Divider(color = Color.Gray, thickness = 1.dp)
                        Day(day = "Понедельник, 14 февраля")
                        Divider(color = Color.Gray, thickness = 1.dp)

                        Item(
                            time_start = "8:30",
                            time_end = "10:05",
                            location = "Корпус №15, ауд. 5-12, ЭИОС",
                            lesson = "ПРОФЕССИОНАЛЬНО-ОРИЕНТИРОВАННЫЙ ИНОСТРАННЫЙ ЯЗЫК",
                            teacher = "Ямских Т.Н."
                        )
                        Item(
                            time_start = "10:15",
                            time_end = "11:50",
                            location = "Корпус №17, ауд. 3-12",
                            lesson = "РАЗРАБОТКА МОБИЛЬНЫХ ПРИЛОЖЕНИЙ",
                            teacher = "Кузнецов А.С."
                        )
                        Item(
                            time_start = "12:00",
                            time_end = "13:35",
                            location = "Корпус №17, ауд. 3-12",
                            lesson = "МАШИННО_ЗАВИСИМЫЕ ЯЗЫКИ ПРОГРАММИРОВАНИЯ",
                            teacher = "Сарамуд М.В."
                        )
                        Item(
                            time_start = "14:10",
                            time_end = "15:45",
                            location = "Корпус №17, ауд. 3-24",
                            lesson = "ФИЗИЧЕСКАЯ КУЛЬТУРА И СПОРТ",
                            teacher = "Брюханова Н.А."
                        )
                        Item(
                            time_start = "15:55",
                            time_end = "17:30",
                            location = "Корпус №17, ауд. 3-24",
                            lesson = "ФИЗИЧЕСКАЯ КУЛЬТУРА И СПОРТ",
                            teacher = "Брюханова Н.А."
                        )
                        Item(
                            time_start = "17:40",
                            time_end = "19:15",
                            location = "Корпус №17, ауд. 3-24",
                            lesson = "ФИЗИЧЕСКАЯ КУЛЬТУРА И СПОРТ",
                            teacher = "Брюханова Н.А."
                        )


                        Spacer(modifier = Modifier.padding(top = 50.dp))
                    }
                }
            }

        }
    }
}



@Composable
fun Group(group: String) {
    Text(
        text = group, style = TextStyle(
            color = Orange,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center,
            fontSize = 20.sp
        ), modifier = Modifier.padding(top = 10.dp, start = 10.dp)
    )
}

@Composable
fun Item(time_start: String, time_end: String, location: String, lesson: String, teacher: String) {

    Box(modifier = Modifier.padding(15.dp)) {
//        Row(
//            modifier = Modifier.fillMaxWidth(),
//            horizontalArrangement = Arrangement.SpaceBetween
//        ){
//            Spacer(modifier = Modifier.padding(0.dp))
//
//
//            Box(
//                modifier = Modifier
//                    .size(50.dp)
//                    .border(
//                        1.dp, color = Color.Blue,
//                        shape = CircleShape
//                    ), contentAlignment = Alignment.TopEnd
//            ) {
//                Text("окно")
//            }
//
//        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(5.dp),
            verticalAlignment = Alignment.CenterVertically


        ) {
            Column() {
                Text(
                    text = time_start, style = TextStyle(
                        color = Color.Black,
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Center
                    )
                )
                Text(
                    text = time_end, style = TextStyle(
                        color = Color.Black,
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Center
                    )
                )
            }
            Column(modifier = Modifier.offset(15.dp)) {
                Text(
                    text = location, style = TextStyle(
                        color = Color.Black,

                        fontSize = 15.sp
                    ), modifier = Modifier.padding(1.dp)
                )
                Text(
                    text = lesson, style = TextStyle(
                        color = Color.Black,
                        fontWeight = FontWeight.Bold,

                        fontSize = 15.sp
                    ), modifier = Modifier.padding(1.dp)
                )
                Text(
                    text = teacher, style = TextStyle(
                        color = Color.Black,

                        fontSize = 15.sp
                    ), modifier = Modifier.padding(1.dp)
                )
            }

        }


    }
    Divider(color = Color.Gray, thickness = 1.dp)
}
