package com.example.duelgraph

import android.util.Log
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.draw
import androidx.compose.ui.text.TextLayoutInput
import androidx.compose.ui.text.TextLayoutResult
import androidx.compose.ui.text.drawText
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.duelgraph.data.DataSet
import kotlinx.coroutines.flow.collectLatest

val viewModel = ViewModel()

@Composable
fun LineGraph(
    list: ArrayList<DataSet>,
    interval: Int = 100,
    modifier: Modifier
) {
    val scrollState = rememberScrollState()
    var term = interval
    Box(
        modifier = modifier
            .fillMaxSize()
            .horizontalScroll(scrollState)
    ) {
        Canvas(
            modifier = Modifier
                .width((100*(list.size)).dp)
                .fillMaxHeight()
                .padding(30.dp)
        ) {
            val num = list.size
            val width = size.width
            val height = size.height
            term = (width / num).toInt()

            // y = 0 직선 그리기
            drawLine(Color.LightGray, Offset(0f, height / 2), Offset(width * (num - 1) / num, height / 2), strokeWidth = 5f)
            list.forEachIndexed { index, data ->
                // 뒤에 수직 회색 선 그리기
                drawLine(Color.LightGray, Offset(width * (index) / num, 0f), Offset(width * (index) / num, height), strokeWidth = 5f)

                drawCircle(
                    Color.Red,
                    20f,
                    Offset(width * (index) / num, height/2 - data.y.toFloat() * height / 20)
                )

                // x 값 표시

                if (index < list.size - 1){
                    drawLine(
                        color = Color.Red,
                        start = Offset(width * (index) / num, height/2 - data.y.toFloat() * height / 20),
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
            scrollState.animateScrollTo((it-2) * term)
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
    LineGraph(list, 100, Modifier.fillMaxSize())
}