package com.example.jetpackcomposeui

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.example.jetpackcomposeui.ui.theme.JetpackComposeUITheme
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.util.Log
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch

class SideEffects : ComponentActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
       // enableEdgeToEdge()
        setContent {
            UserDashboardScreen(sensorManager = getSystemService(SENSOR_SERVICE) as SensorManager)

        }
    }

    @OptIn(ExperimentalMaterial3Api::class)
    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    @Composable
    fun UserDashboardScreen(sensorManager: SensorManager) {

        var user by remember { mutableStateOf<User?>(null) }
        var posts by remember { mutableStateOf<List<Post>>(emptyList()) }
        val coroutineScope = rememberCoroutineScope()

        // ✅ 1️⃣ LaunchedEffect - API Call when screen loads
        LaunchedEffect(Unit) {
            user = RetrofitInstance.api.getUser()
            posts = RetrofitInstance.api.getUserPosts(user?.id ?: 1)
        }

        // ✅ 2️⃣ produceState - Fetch data from API and store it in State
        val userName = produceState(initialValue = "Loading...") {
            value = RetrofitInstance.api.getUser().name
        }

        // ✅ 3️⃣ DisposableEffect - Add/Remove Sensor Listener
        var sensorValue by remember { mutableStateOf(0f) }
        DisposableEffect(sensorManager) {
            val sensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER)
            val listener = object : SensorEventListener {
                override fun onSensorChanged(event: SensorEvent?) {
                    sensorValue = event?.values?.get(0) ?: 0f
                }
                override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {}
            }
            sensorManager.registerListener(listener, sensor, SensorManager.SENSOR_DELAY_NORMAL)

            onDispose {
                sensorManager.unregisterListener(listener)
            }
        }

        // ✅ 4️⃣ SideEffect - Log every time UI is recomposed
        SideEffect {
            Log.d("Compose", "UI Updated! User: ${user?.name}, Sensor: $sensorValue")
        }

        // ✅ 5️⃣ derivedStateOf - Counting the number of posts to optimize performance
        val postCount by remember {
            derivedStateOf { posts.size }
        }

        Scaffold(
            topBar = { TopAppBar(title = { Text("User Dashboard") }) }
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                Text(text = "User: ${userName.value}", style = MaterialTheme.typography.displayMedium)
                Text(text = "Email: ${user?.email ?: "Fetching..."}")
                Text(text = "Total Posts: $postCount")
                Text(text = "Accelerometer: $sensorValue")

                Spacer(modifier = Modifier.height(10.dp))

                // ✅ 6️⃣ Coroutine Scope - API Call occurs when "Refresh" button is pressed
                Button(onClick = {
                    coroutineScope.launch {
                        user = RetrofitInstance.api.getUser()
                        posts = RetrofitInstance.api.getUserPosts(user?.id ?: 1)
                    }
                }) {
                    Text("Refresh Data")
                }

                Spacer(modifier = Modifier.height(10.dp))

                LazyColumn {
                    items(posts) { post ->
                        Card(modifier = Modifier.padding(8.dp)) {
                            Column(modifier = Modifier.padding(8.dp)) {
                                Text(text = post.title, style = MaterialTheme.typography.bodyMedium)
                            }
                        }
                    }
                }
            }
        }
    }



    @Preview(showBackground = true)
    @Composable
    fun PreviewSimpleUIExample() {
        UserDashboardScreen(sensorManager = getSystemService(SENSOR_SERVICE) as SensorManager)
    }
}
