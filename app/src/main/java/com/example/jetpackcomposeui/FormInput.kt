package com.example.jetpackcomposeui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.jetpackcomposeui.ui.theme.JetpackComposeUITheme

class FormInput : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            JetpackComposeUITheme {
                MyApp()
            }
        }
    }

    @Composable
    fun MyApp() {
        MaterialTheme {
            Surface(
                modifier = Modifier.fillMaxSize()
            ) {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center // ✅ Centers everything
                ) {
                    CheckboxRadioButtonAndSwitchExample()
                }
            }
        }
    }

    @Composable
    fun CheckboxRadioButtonAndSwitchExample() {
        var isChecked by remember { mutableStateOf(false) }
        var selectedOption by remember { mutableStateOf("Male") }
        var isSwitchOn by remember { mutableStateOf(false) }

        Column(
            modifier = Modifier
                .wrapContentSize() // ✅ Ensures it takes only needed space
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally, // ✅ Centers content horizontally
            verticalArrangement = Arrangement.Center // ✅ Centers items inside the Column
        ) {
            // ✅ Checkbox
            Row(verticalAlignment = Alignment.CenterVertically) {
                Checkbox(checked = isChecked, onCheckedChange = { isChecked = it })
                Spacer(modifier = Modifier.width(8.dp))
                Text(text = "Accept Terms & Conditions")
            }

            Spacer(modifier = Modifier.height(16.dp))

            // ✅ Radio Buttons
            Text("Select Gender:")
            Row(verticalAlignment = Alignment.CenterVertically) {
                RadioButton(
                    selected = selectedOption == "Male",
                    onClick = { selectedOption = "Male" }
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text("Male")

                Spacer(modifier = Modifier.width(16.dp))

                RadioButton(
                    selected = selectedOption == "Female",
                    onClick = { selectedOption = "Female" }
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text("Female")
            }

            Spacer(modifier = Modifier.height(16.dp))

            // ✅ Switch Button
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(text = "Enable Notifications")
                Spacer(modifier = Modifier.width(8.dp))
                Switch(
                    checked = isSwitchOn,
                    onCheckedChange = { isSwitchOn = it }
                )
            }

            Spacer(modifier = Modifier.height(16.dp))
            Text("Selected Option: $selectedOption")
            Text("Switch is: ${if (isSwitchOn) "ON" else "OFF"}") // ✅ Shows switch state
        }
    }



    @Preview(showBackground = true)
    @Composable
    fun PreviewSimpleUIExample() {
        MyApp()
    }
}
