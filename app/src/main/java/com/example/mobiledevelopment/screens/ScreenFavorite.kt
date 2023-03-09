package com.example.mobiledevelopment.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.mobiledevelopment.ui.theme.Gray
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.pagerTabIndicatorOffset
import com.google.accompanist.pager.rememberPagerState
import kotlinx.coroutines.launch

@Preview(showBackground = true)
@Composable
fun ScreenFavorite() {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Box(modifier = Modifier.fillMaxWidth().padding(10.dp), contentAlignment = Alignment.CenterStart) {
            Text(text = "Избранное", style = TextStyle(fontWeight = FontWeight.Bold), fontSize = 20.sp)
        }
        TabLayout1()

    }
}


@OptIn(ExperimentalPagerApi::class)
@Composable
private fun TabLayout1() {
    val tabList = listOf("Группы", "Преподаватели")
    val pagerState = rememberPagerState()
    val tabIndex = pagerState.currentPage
    val coroutineScope = rememberCoroutineScope()
    Column(
        modifier = Modifier
            .padding(5.dp)
            .clip(RoundedCornerShape(10.dp))
    ) {
        TabRow(
            selectedTabIndex = tabIndex,
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
            val list = when (index) {
                0 -> {
                    Column(modifier = Modifier.fillMaxSize(), ) {
                        ItemGroup(str = "КИ20-17/1Б (1 подгруппа)")
                        ItemGroup(str = "КИ20-16/1Б (2 подгруппа)")
                        ItemGroup(str = "КИ20-17/2Б (1 подгруппа)")
                        ItemGroup(str = "КИ20-16/2Б (2 подгруппа)")
                    }

                }
                1 -> {
                }
                else -> {
                }
            }
        }

    }
}
@Composable
fun ItemGroup(str: String){
    Column() {
        Row(horizontalArrangement = Arrangement.SpaceBetween, modifier = Modifier
            .fillMaxWidth()
            .padding(top = 10.dp, start = 10.dp, end = 10.dp)) {
            Text(text = str)
            Text(text = ">")
        }
        Divider(modifier = Modifier
            .padding(top = 10.dp)
            .size(10000.dp, 1.dp), color = Gray
        )
    }
}
