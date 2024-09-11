package com.example.duelgraph

import android.util.Log
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Paint
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.duelgraph.data.DataSet
import kotlinx.coroutines.flow.collectLatest

@Composable
fun LineGraph(
    list: ArrayList<DataSet>,
    modifier: Modifier
) {
    val viewModel = ViewModel()
    val scrollState = rememberScrollState()
    Box(
        modifier = modifier
            .fillMaxSize()
            .horizontalScroll(scrollState)
    ) {
        Canvas(
            modifier = Modifier
                .width((50*list.size).dp)
                .fillMaxHeight()
        ) {
            val num = list.size
            val paint = Paint().apply {
                color = Color.Red
                strokeWidth = 5f
            }
            val width = size.width
            val height = size.height
            list.forEachIndexed { index, data ->
                drawCircle(
                    Color.Red,
                    20f,
                    Offset(width * index / num, height/2 - data.y.toFloat() * height / 20)
                )
                if (index < list.size - 1){
                    drawLine(
                        color = Color.Red,
                        start = Offset(width * index / num, height/2 - data.y.toFloat() * height / 20),
                        end = Offset(width * (index + 1) / num, height/2 - list[index + 1].y.toFloat() * height / 20),
                        strokeWidth = 5f
                    )
                }
            }
        }
    }

    LaunchedEffect(Unit) {
        viewModel.state.collectLatest {
            Log.d("state", it.toString())
        }
    }
}

@Preview(showBackground = true)
@Composable
fun Preview() {
    val list = ArrayList<DataSet>()
    list.apply {
        add(DataSet("1", "2"))
        add(DataSet("2", "3"))
        add(DataSet("3", "4"))
        add(DataSet("4", "5"))
    }
    LineGraph(list, Modifier.fillMaxSize())
}