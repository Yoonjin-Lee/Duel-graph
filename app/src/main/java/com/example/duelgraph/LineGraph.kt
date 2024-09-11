package com.example.duelgraph

import android.util.Size
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.foundation.Canvas
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Canvas
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Paint
import androidx.compose.ui.graphics.drawscope.Stroke

@Composable
fun LineGraph(
    list : ArrayList<com.example.duelgraph.data.DataSet>,
    modifier: Modifier
){
    val scrollState = rememberScrollState()
    val scope = rememberCoroutineScope()
    Box(
        modifier = modifier
            .verticalScroll(scrollState)
            .fillMaxSize()
    ) {
        Canvas(modifier = Modifier.fillMaxSize()) {

        }
    }
}