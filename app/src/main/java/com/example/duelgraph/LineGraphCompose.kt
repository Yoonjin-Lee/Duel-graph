package com.example.duelgraph

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.duelgraph.data.DataSet
import kotlinx.coroutines.flow.collectLatest

@Composable
fun LineGraphCompose(
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
        Box(
            modifier = modifier
                .width((100 * (list.size)).dp)
                .fillMaxHeight()
                .padding(30.dp, 0.dp)
                .background(Color.Gray)
                .drawBehind {
                    val num = list.size
                    val width = size.width
                    val height = size.height
                    term = (width / num).toInt()

                    // y = 0 직선 그리기
                    drawLine(
                        Color.LightGray,
                        Offset(0f, height / 2),
                        Offset(width * (num - 1) / num, height / 2),
                        strokeWidth = 5f
                    )
                    list.forEachIndexed { index, data ->
                        // 뒤에 수직 회색 선 그리기
                        drawLine(
                            Color.LightGray,
                            Offset(width * (index) / num, 0f),
                            Offset(width * (index) / num, height),
                            strokeWidth = 5f
                        )

                        drawCircle(
                            Color.Red,
                            20f,
                            Offset(
                                width * (index) / num,
                                height / 2 - data.y.toFloat() * height / 20
                            )
                        )

                        // x 값 표시

                        if (index < list.size - 1) {
                            drawLine(
                                color = Color.Red,
                                start = Offset(
                                    width * (index) / num,
                                    height / 2 - data.y.toFloat() * height / 20
                                ),
                                end = Offset(
                                    width * (index + 1) / num,
                                    height / 2 - list[index + 1].y.toFloat() * height / 20
                                ),
                                strokeWidth = 5f
                            )
                        }
                    }
                }
        )
    }

    LaunchedEffect(Unit) {
        viewModel.state.collectLatest {
            Log.d("state", it.toString())
            Log.d("ScrollState", "Scroll position: $scrollState")
            scrollState.animateScrollTo((it - 2) * term)
        }
    }
}