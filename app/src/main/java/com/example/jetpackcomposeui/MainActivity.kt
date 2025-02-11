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
import androidx.compose.material.icons.filled.AppBlocking
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
import com.example.jetpackcomposeui.ui.theme.Pink40


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            JetpackComposeUITheme {
                LoginScreen()
            }
        }
    }
}

@Composable
fun SimpleUIExample() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp), // ✅ Overall margin (padding)
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        // ✅ ImageView
        Image(
            painter = painterResource(id = R.drawable.ic_launcher_foreground), // Replace with your image
            contentDescription = "App Logo",
            modifier = Modifier
                .size(100.dp)
                .padding(bottom = 16.dp) // ✅ Bottom margin
        )

        // ✅ Title TextView
        Text(
            text = "Welcome to Jetpack Compose",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(bottom = 8.dp) // ✅ Bottom margin
        )

        // ✅ Subtitle TextView
        Text(
            text = "Let's build a modern UI with Compose!",
            fontSize = 16.sp,
            color = Pink40,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(bottom = 16.dp) // ✅ Bottom margin
        )

        // ✅ Button
        Button(
            onClick = { /* TODO: Handle Click */ },
            modifier = Modifier
                .fillMaxWidth(0.8f) // ✅ Button width
                .padding(10.dp) // ✅ Button margin
        ) {
            Text(text = "Get Started")
        }

        // ✅ Button
        Button(
            onClick = { /* TODO: Handle Click */ },
            shape = RoundedCornerShape(10.dp),
            modifier = Modifier
                .fillMaxWidth(0.8f) // ✅ Button width
                .padding(10.dp) // ✅ Button margin
        ) {
            Text(text = "Circular Started")
        }
    }
}

@Composable
fun MyScreen() {
    var text by remember { mutableStateOf("Hello, Jetpack Compose!") }

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = text, fontSize = 20.sp, color = Color.Black)
        Button(onClick = {
            text = "Button Clicked!"
        }) {
            Text(text = "Click Me")
        }
    }
}


@Composable
fun WeightExample() {
    Row(modifier = Modifier
        .fillMaxSize()
        .fillMaxWidth()) {
        Text(
            text = "Weight 1",
            modifier = Modifier
                .weight(1f)
                .background(Color.Blue)
                .padding(16.dp),
            color = Color.White
        )

        Text(
            text = "Weight 2",
            modifier = Modifier
                .weight(1f)
                .background(Color.Green)
                .padding(16.dp),
            color = Color.White
        )

        Text(
            text = "Weight 2",
            modifier = Modifier
                .weight(1f)
                .background(Color.Red)
                .padding(16.dp),
            color = Color.White
        )
    }
}


@Composable
fun ListExample() {
    val items = listOf("Item 1", "Item 2", "Item 3")

    LazyColumn(
        modifier = Modifier.fillMaxSize())
    {
        items(items) { item ->
            Text(text = item, fontSize = 20.sp, modifier = Modifier.padding(8.dp))
        }
    }
}


//✅ Row (Horizontal Alignment Example)

@Composable
fun RowExample() {
    Row(
        modifier = Modifier.fillMaxSize(),
        horizontalArrangement = Arrangement.SpaceEvenly // Space between elements
    ) {
        Text(text = "Item 1")
        Text(text = "Item 2")
        Text(text = "Item 3")
    }
}

@Composable
fun RowExample1() {
    Row(
        modifier = Modifier.fillMaxSize(),
        horizontalArrangement = Arrangement.SpaceEvenly //Equal space between elements
    ) {
        Text(text = "Item 1", modifier = Modifier.weight(1f))
        Text(text = "Item 2", modifier = Modifier.weight(1f))
        Text(text = "Item 3", modifier = Modifier.weight(1f))
    }
}

// Column (Vertical Alignment Example)

@Composable
fun ColumnExample() {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.SpaceEvenly // Space between elements vertically
    ) {
        Text(text = "Top")
        Text(text = "Middle")
        Text(text = "Bottom")
    }
}

@Composable
fun ColumnExample1() {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.SpaceBetween, // ✅ Space evenly distribute karega
        horizontalAlignment = Alignment.CenterHorizontally // ✅ Center align karega
    ) {
        Text(text = "Top Element", fontSize = 18.sp)
        Text(text = "Middle Element", fontSize = 18.sp)
        Text(text = "Bottom Element", fontSize = 18.sp)
    }

    Text("Hello", modifier = Modifier
        .padding(16.dp)
        .background(Color.Red))


}

// Box (Overlay Items Example)

@Composable
fun BoxExample() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.BottomEnd  //Text bottom right corner
    ) {
        Text(text = "Bottom Right", fontSize = 18.sp)
    }
}

@Composable
fun BoxExample1() {
    Box(
        modifier = Modifier
            .fillMaxSize() // full screen cover
            .fillMaxSize()
            .padding(end = 16.dp, bottom = 16.dp), // ✅ FAB  padding
        contentAlignment = Alignment.BottomEnd // ✅ FAB  Bottom-Right align
    ) {
        FloatingActionButton(
            onClick = {

            },
            modifier = Modifier.padding(20.dp) // ✅ FAB के अंदर Padding
        ) {
            Icon(Icons.Default.Add, contentDescription = "Add")
        }
    }
}

@Composable
fun BoxExample2() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center // ✅ Center with content
    ) {
        Image(painter = painterResource(id = R.drawable.ic_launcher_background), contentDescription = null)
        Text(text = "Overlay Text", color = Color.White)
    }
}




//ConstraintLayout

@Composable
fun ConstraintLayoutExample() {
    ConstraintLayout(
        modifier = Modifier.fillMaxSize()
    ) {
        val (button1, button2, text, text1) = createRefs() // Creating references to components


        Button(
            onClick = {},
            modifier = Modifier.constrainAs(button1) {
                top.linkTo(parent.top, margin = 16.dp) // Top 16dp margin
                start.linkTo(parent.start)
                end.linkTo(parent.end)
            }
        ) {
            Text("Button 1")
        }

        Button(
            onClick = {},
            modifier = Modifier.constrainAs(button2) {
                top.linkTo(button1.bottom, margin = 16.dp) // Button 1  bottom  16dp distance
                start.linkTo(button1.start)
                end.linkTo(button1.end)
            }
        ) {
            Text("Button 2")
        }

        Text(
            text = "Hello Compose!",
            modifier = Modifier.constrainAs(text) {
                top.linkTo(parent.top, margin = 10.dp)
                start.linkTo(parent.start, margin = 10.dp)
                end.linkTo(parent.end, margin = 10.dp)
                bottom.linkTo(parent.bottom, margin = 10.dp)
            }
        )

        Text(
            text = "Hello Compose!",
            modifier = Modifier.constrainAs(text1) {
                top.linkTo(parent.top, margin = 10.dp)
                start.linkTo(parent.start, margin = 10.dp)
                end.linkTo(parent.end, margin = 10.dp)
                bottom.linkTo(parent.bottom, margin = 10.dp)
            }
        )
    }
}


@Composable
fun DesignPrinciples() {
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        Text(text = "Top Element", fontSize = 18.sp)
        Text(text = "Middle Element", fontSize = 18.sp)
        Text(text = "Bottom Element", fontSize = 18.sp)

        //wrong way
        Text("Hello", modifier = Modifier.padding(16.dp).background(Color.Red).padding(8.dp))

        // Right way
        Text("Hello", modifier = Modifier.background(Color.Red).padding(16.dp))

    }
}

@Composable
fun CustomButton(text: String) {
    Button(
        onClick = { /* TODO */ }, // ✅ Click event
        modifier = Modifier.fillMaxWidth() // ✅ Full screen width & height
    ) {
        Text(text = text)
    }
}


// State and Recomposition

@Composable
fun Counter() {
    var count by remember { mutableStateOf(0) } // ✅ State variable

    Box(
        modifier = Modifier
            .fillMaxSize(), // ✅ Full Screen लेगा
        contentAlignment = Alignment.Center // ✅ Center में Align करेगा
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally, // ✅ Center में Align करेगा
            verticalArrangement = Arrangement.Center // ✅ Column के अंदर भी Center करेगा
        ) {
            Text(text = "Count: $count", fontSize = 24.sp) // ✅ Count को बड़ा दिखाने के लिए

            Spacer(modifier = Modifier.height(16.dp)) // ✅ Button और Text के बीच space देगा

            Button(
                onClick = { count++ },
                modifier = Modifier.fillMaxWidth(0.5f) // ✅ Button को 50% width देगा
            ) {
                Text("Increment", fontSize = 18.sp)
            }
        }
    }
}


@Composable
fun MaterialUI() {
    MaterialTheme {
        Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
            Text(text = "Hello Material Design", style = MaterialTheme.typography.titleLarge)
            Button(onClick = {}) {
                Text("Click Me")
            }
        }
    }
}


@Composable
fun ItemList() {
    LazyColumn(
        modifier = Modifier.fillMaxSize() // ✅ Full Screen
    ) {
        items(30) { index ->
            Text(
                text = "Item $index",
                modifier = Modifier
                    .fillMaxWidth() // To stretch the text to the entire row width
                    .padding(16.dp)
            )
        }
    }
}

@Composable
fun HorizontalItemList() {
    LazyRow(
        modifier = Modifier.fillMaxSize() // ✅ Full Screen
    ) {
        items(10) { index ->
            Text(
                text = "Item $index",
                modifier = Modifier.padding(16.dp).fillMaxWidth()
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyScaffoldScreen() {
    Scaffold(
        topBar = {
            TopAppBar(title = { Text("My App") })
        },
        floatingActionButton = {
            FloatingActionButton(onClick = {}) {
                Icon(Icons.Default.Add, contentDescription = "Add")
            }
        },
        bottomBar = {
            BottomAppBar {
                Text("Bottom Navigation")
            }
        }
    ) { innerPadding ->
        Column(modifier = Modifier.padding(innerPadding)) {
            Text("Hello Scaffold!", modifier = Modifier.padding(16.dp))
        }
    }
}

@Composable
fun LoginScreen() {

    var text1 by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = "Login", style = MaterialTheme.typography.displayLarge)
        Spacer(modifier = Modifier.height(16.dp))
        TextField(value = "", onValueChange = {text1 = it}, label = { Text("Email") })
        Spacer(modifier = Modifier.height(8.dp))
        TextField(
            value = "",
            onValueChange = {},
            label = { Text("Password") },
            visualTransformation = PasswordVisualTransformation()
        )
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = { /* Login Action */ }) {
            Text(text = "Login")
        }
    }

}

@Composable
fun GetTextFieldValue() {
    var text by remember { mutableStateOf("") }


    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        TextField(
            value = text,
            onValueChange = { text = it },
            label = { Text("Enter something") }
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text(text = "You entered: $text", style = MaterialTheme.typography.bodyLarge)
    }

}


@Preview(showBackground = true)
@Composable
fun PreviewSimpleUIExample() {
    GetTextFieldValue()
}


