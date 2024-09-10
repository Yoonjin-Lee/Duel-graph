package com.example.duelgraph

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import com.example.duelgraph.data.DataSet

@Composable
fun LineGraph(
    list : ArrayList<DataSet>,
    modifier: Modifier
){
    val scrollState = rememberScrollState()
    val scope = rememberCoroutineScope()
    Row(
        modifier = modifier.verticalScroll(scrollState),

    ) {

    }
}