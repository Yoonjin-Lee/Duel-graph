package com.example.duelgraph

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.duelgraph.ui.theme.DuelGraphTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            DuelGraphTheme {
                val list = ArrayList<com.example.duelgraph.data.DataSet>()
                list.add(com.example.duelgraph.data.DataSet("1", "2"))
                DualGraph(list)
            }
        }
    }
}

@Composable
fun DualGraph(list: ArrayList<com.example.duelgraph.data.DataSet>) {
    Scaffold(
        modifier = Modifier.fillMaxSize()
    ) {
        Row(
            modifier = Modifier.padding(it)
                .fillMaxSize()
        ) {
            LineGraph(
                list,
                modifier = Modifier.weight(0.8f)
                    .fillMaxHeight()
            )
            ScrollGraph(
                list,
                modifier = Modifier.weight(0.2f)
                    .fillMaxHeight()
            )
        }
    }
}

@Composable
fun ScrollGraph(
    list : ArrayList<com.example.duelgraph.data.DataSet>,
    modifier: Modifier
){
    Box(
        modifier = modifier
    ) {
        LazyColumn{
            items(list.size){
                ScrollCard(list[it])
            }
        }
    }
}

@Composable
fun ScrollCard(data : com.example.duelgraph.data.DataSet){
    Box(
        modifier = Modifier.fillMaxSize().clickable {

        }
    ) {
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            Text(
                modifier = Modifier.fillMaxWidth(),
                text = data.y,
                textAlign = TextAlign.Start,
                color = Color.Red,
                fontSize = 20.sp
            )
            HorizontalDivider(
                modifier = Modifier.fillMaxWidth(),
                color = Color.LightGray,
                thickness = 1.dp
            )
            Text(
                modifier = Modifier.fillMaxWidth(),
                text = data.x,
                textAlign = TextAlign.End,
                color = Color.Blue,
                fontSize = 14.sp
            )
        }
    }
}


@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    DuelGraphTheme {
        val list = ArrayList<com.example.duelgraph.data.DataSet>()
        list.add(com.example.duelgraph.data.DataSet("1", "2"))
        list.add(com.example.duelgraph.data.DataSet("1", "2"))
        list.add(com.example.duelgraph.data.DataSet("1", "2"))
        list.add(com.example.duelgraph.data.DataSet("1", "2"))
        DualGraph(list)
    }
}