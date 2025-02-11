package com.example.jetpackcomposeui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.Role.Companion.Image
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import com.example.jetpackcomposeui.ui.theme.GrayNew
import com.example.jetpackcomposeui.ui.theme.JetpackComposeUITheme



class StateRecomposition  : ComponentActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            JetpackComposeUITheme {
                CounterScreen()
            }
        }
    }

    // Example - Simple State

    @Composable
    fun Counter() {
        var count by remember { mutableStateOf(0) }  // ✅ State

        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = "Count: $count", fontSize = 24.sp)

            Button(onClick = { count++ }) {
                Text("Increment")
            }
        }
    }

   // Recomposition

    @Composable
    fun MyComposable() {
        var text by remember { mutableStateOf("Hello") }

        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        )  {
            Text(text, fontSize = 24.sp)
            Button(onClick = { text = "Updated!" }) {
                Text("Click Me")
            }
        }
    }

    //State Hoisting

    // Without State Hoisting (Bad Practice)

    @Composable
    fun Counter1() {
        var count by remember { mutableStateOf(0) } // ✅ State Variable

        Box(
            modifier = Modifier.fillMaxSize(), // ✅ Full Screen
            contentAlignment = Alignment.Center // ✅ Button  Center  Align
        ) {
            Button(
                onClick = { count++ },
                modifier = Modifier.fillMaxWidth(0.5f) // ✅ Button  50% Width
            ) {
                Text("Count: $count", fontSize = 18.sp)
            }
        }
    }

    // With State Hoisting (Good Practice)

    @Composable
    fun Counter(count: Int, onIncrement: () -> Unit) { // ✅ State hoisted
        Button(
            onClick = onIncrement,
            modifier = Modifier.fillMaxWidth(0.5f) // ✅ Button को 50% Width देगा
        ) {
            Text("Count: $count", fontSize = 18.sp)
        }
    }

    @Composable
    fun CounterScreen() {
        var count by remember { mutableStateOf(0) } // ✅ State yahan hai

        Box(
            modifier = Modifier.fillMaxSize(), // ✅ Full Screen लेगा
            contentAlignment = Alignment.Center // ✅ Center में Align करेगा
        ) {
            Counter(count, onIncrement = { count++ }) // ✅ State ko pass kar diya
        }
    }



    @Preview(showBackground = true)
    @Composable
    fun PreviewSimpleUIExample() {
        CounterScreen()
    }

}