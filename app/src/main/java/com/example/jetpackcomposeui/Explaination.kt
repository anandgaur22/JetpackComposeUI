package com.example.jetpackcomposeui

/*


Summary (Jetpack Compose vs XML Margin Comparison)

XML Property	                         Jetpack Compose Equivalent

android:layout_margin="10dp"	         Modifier.padding(10.dp)
android:layout_marginStart="10dp"	     Modifier.padding(start = 10.dp)
android:layout_marginEnd="10dp"	         Modifier.padding(end = 10.dp)
android:layout_marginTop="10dp"	         Modifier.padding(top = 10.dp)
android:layout_marginBottom="10dp"	     Modifier.padding(bottom = 10.dp)
android:layout_marginHorizontal="10dp"	 Modifier.padding(horizontal = 10.dp)
android:layout_marginVertical="10dp"	 Modifier.padding(vertical = 10.dp)


Jetpack Compose vs XML (Comparison)

Feature	                 XML (Traditional)	                     Jetpack Compose

ImageView	             <ImageView> + src	                    Image(painterResource(...))
TextView	             <TextView>	                            Text(text = "...")
Button	                 <Button>	                            Button(onClick = {...})
Margin	                 layout_margin="16dp"	                Modifier.padding(16.dp)
Alignment	             android:gravity="center"	            horizontalAlignment = Alignment.CenterHorizontally

Modifier in Jetpack Compose

XML Property	                                             Jetpack Compose Modifier
android:layout_width="match_parent"	                         Modifier.fillMaxWidth()
android:layout_height="wrap_content"	                     Modifier.wrapContentHeight()
android:padding="16dp"	                                     Modifier.padding(16.dp)
android:layout_margin="8dp"	                                 Modifier.padding(8.dp)
android:gravity="center"	                                 Modifier.align(Alignment.CenterHorizontally)


Commonly Used Modifiers in Jetpack Compose

Modifier	                   Use Case
padding()	              Adds space inside the element
fillMaxSize()	          Fills the entire available space
fillMaxWidth()	          Takes up the full width of the parent
fillMaxHeight()	          Takes up the full height of the parent
wrapContentSize()	      Wraps the content based on its size
background()	          Sets a background color or gradient
border()	              Adds a border to the element
clip()	                  Defines a shape (e.g., Rounded Corners)
size()	                  Sets a fixed width and height
width()	                  Sets only the width
height()	              Sets only the height
clickable()	              Adds click functionality
align()	                  Aligns the element within its parent
offset()	              Moves the element to a specific position
alpha()	                  Sets transparency or opacity
rotate()	              Rotates the element
scale()	                  Increases or decreases the element's size










//LaunchedEffect

@Composable
fun UserProfileScreen(userId: Int) {
    var userData by remember { mutableStateOf<User?>(null) }

    LaunchedEffect(userId) { // userId change होने पर यह फिर से चलेगा
        userData = fetchUserData(userId)
    }

    Text(text = userData?.name ?: "Loading...")
}

suspend fun fetchUserData(userId: Int): User {
    delay(2000) // Simulating network delay
    return User("Anand Gaur", 30)
}


// rememberCoroutineScope

@Composable
fun UserProfile() {
    val coroutineScope = rememberCoroutineScope()
    var userData by remember { mutableStateOf<User?>(null) }

    Column {
        Button(onClick = {
            coroutineScope.launch {
                userData = fetchUserData(1)
            }
        }) {
            Text("Fetch User Data")
        }
        Text(text = userData?.name ?: "No Data")
    }
}

// DisposableEffect

@Composable
fun SensorListener(sensorManager: SensorManager) {
    DisposableEffect(Unit) {
        val sensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER)
        val listener = object : SensorEventListener {
            override fun onSensorChanged(event: SensorEvent?) {
                // Handle sensor data
            }

            override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {}
        }

        sensorManager.registerListener(listener, sensor, SensorManager.SENSOR_DELAY_NORMAL)

        onDispose {
            sensorManager.unregisterListener(listener)
        }
    }
}

// SideEffect

@Composable
fun Counter() {
    var count by remember { mutableStateOf(0) }

    SideEffect {
        Log.d("Counter", "Current count: $count")
    }

    Button(onClick = { count++ }) {
        Text("Count: $count")
    }
}

// produceState

@Composable
fun WeatherScreen(city: String) {
    val weather = produceState(initialValue = "Loading...", city) {
        value = fetchWeather(city)
    }

    Text(text = "Weather in $city: ${weather.value}")
}

suspend fun fetchWeather(city: String): String {
    delay(2000) // Simulating network delay
    return "Sunny 25°C"
}

// derivedStateOf

@Composable
fun FilteredList(searchQuery: String, items: List<String>) {
    val filteredItems by remember {
        derivedStateOf {
            items.filter { it.contains(searchQuery, ignoreCase = true) }
        }
    }

    LazyColumn {
        items(filteredItems) { item ->
            Text(item)
        }
    }
}

















*/
