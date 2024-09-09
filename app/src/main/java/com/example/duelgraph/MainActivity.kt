package com.example.duelgraph

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyItemScope
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.example.duelgraph.data.DataSet
import com.example.duelgraph.ui.theme.DuelGraphTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            DuelGraphTheme {
                DualGraph()
            }
        }
    }
}

@Composable
fun DualGraph() {
    Scaffold(
        modifier = Modifier.fillMaxSize()
    ) {
        Row(
            modifier = Modifier.padding(it)
                .fillMaxSize()
        ) {

        }
    }
}

@Composable
fun ScrollGraph(list : ArrayList<DataSet>){
    Box(
        modifier = Modifier.fillMaxHeight()
    ) {
        LazyColumn {
            list.forEach {data ->
                ScrollCard(data)
            }
        }
    }
}

@Composable
fun ScrollCard(data : DataSet){
    Box(
        modifier = Modifier.fillMaxSize()
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


val list = ArrayList<DataSet>();


@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    DuelGraphTheme {
        list.add(DataSet("1", "2"))
        ScrollCard(DataSet("1", "2"))
    }
}