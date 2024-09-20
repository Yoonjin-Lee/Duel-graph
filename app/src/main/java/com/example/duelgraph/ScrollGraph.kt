package com.example.duelgraph

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.duelgraph.data.DataSet

@Composable
fun ScrollGraph(
    list : ArrayList<DataSet>,
    modifier: Modifier
){
    val selectedIndex = remember { mutableIntStateOf(-1) }
    Box(
        modifier = modifier
    ) {
        LazyColumn(
            modifier = Modifier.selectableGroup(),
            verticalArrangement = Arrangement.spacedBy(5.dp)
        ){
            items(list.size){ index ->
                Box(
                    modifier = Modifier.fillMaxSize()
                        .clip(
                            RoundedCornerShape(8.dp)
                        )
                        .selectable(
                            selected = (selectedIndex.intValue == index),
                            onClick = {
                                if(selectedIndex.intValue == index){ // 이미 선택된 것을 선택한 경우
                                    selectedIndex.intValue = -1
                                }else{
                                    viewModel.updateState(index)
                                    selectedIndex.intValue = index
                                }
                            }
                        ).background(
                            if (selectedIndex.intValue == index) Color.LightGray else Color.White
                        ).border(
                            width = 1.dp,
                            color = Color.Gray,
                            shape = RoundedCornerShape(8.dp)
                        )
                ) {
                    Column(
                        modifier = Modifier.fillMaxSize()
                            .padding(10.dp)
                    ) {
                        Text(
                            modifier = Modifier.fillMaxWidth(),
                            text = list[index].y,
                            textAlign = TextAlign.Start,
                            color = Color.Red,
                            fontSize = 25.sp
                        )
                        HorizontalDivider(
                            modifier = Modifier.fillMaxWidth(),
                            color = Color.LightGray,
                            thickness = 1.dp
                        )
                        Text(
                            modifier = Modifier.fillMaxWidth(),
                            text = list[index].x,
                            textAlign = TextAlign.End,
                            color = Color.Blue,
                            fontSize = 20.sp
                        )
                    }
                }
            }
        }
    }
}