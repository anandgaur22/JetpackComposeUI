package com.example.jetpackcomposeui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.*
import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectHorizontalDragGestures
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlin.math.roundToInt
import androidx.compose.ui.draw.shadow
import kotlinx.coroutines.launch
import androidx.compose.material3.*



class AnimationsCompose : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AnimatedListScreen()
        }
    }


    @Composable
    fun ComposeAnimationsApp() {
        Column(
            modifier = Modifier.fillMaxSize().padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            SimpleSizeAnimation()
            Spacer(modifier = Modifier.height(20.dp))
            VisibilityAnimation()
            Spacer(modifier = Modifier.height(20.dp))
            MultiPropertyAnimation()
            Spacer(modifier = Modifier.height(20.dp))
            PulsatingAnimation()
            Spacer(modifier = Modifier.height(20.dp))
            SpringAnimation()
            Spacer(modifier = Modifier.height(20.dp))
            AnimatedButton()
        }
    }

    /* Basic Animation (Size Change) */
    @Composable
    fun SimpleSizeAnimation() {
        var expanded by remember { mutableStateOf(false) }

        val size by animateDpAsState(
            targetValue = if (expanded) 150.dp else 100.dp,
            animationSpec = tween(durationMillis = 500)
        )

        Box(
            modifier = Modifier
                .size(size)
                .background(Color.Blue, shape = CircleShape)
                .clickable { expanded = !expanded },
            contentAlignment = Alignment.Center
        ) {
            Text("Tap Me", color = Color.White, fontSize = 16.sp)
        }
    }

    /* Visibility Animation */
    @Composable
    fun VisibilityAnimation() {
        var visible by remember { mutableStateOf(true) }

        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Button(onClick = { visible = !visible }) {
                Text("Toggle Visibility")
            }

            AnimatedVisibility(
                visible = visible,
                enter = fadeIn() + scaleIn(),
                exit = fadeOut() + scaleOut()
            ) {
                Box(
                    modifier = Modifier
                        .size(100.dp)
                        .background(Color.Red, shape = CircleShape)
                )
            }
        }
    }

    /* Multiple Property Animation (Size & Color) */
    @Composable
    fun MultiPropertyAnimation() {
        var expanded by remember { mutableStateOf(false) }

        val transition = updateTransition(targetState = expanded, label = "Expand Transition")

        val size by transition.animateDp(label = "Size") { if (it) 150.dp else 100.dp }
        val color by transition.animateColor(label = "Color") { if (it) Color.Green else Color.Magenta }

        Box(
            modifier = Modifier
                .size(size)
                .background(color, shape = CircleShape)
                .clickable { expanded = !expanded },
            contentAlignment = Alignment.Center
        ) {
            Text("Tap Me", color = Color.White, fontSize = 16.sp)
        }
    }

    /* Infinite Animation (Pulsating Effect) */
    @Composable
    fun PulsatingAnimation() {
        val infiniteTransition = rememberInfiniteTransition()

        val scale by infiniteTransition.animateFloat(
            initialValue = 0.8f,
            targetValue = 1.2f,
            animationSpec = infiniteRepeatable(
                animation = tween(1000, easing = FastOutSlowInEasing),
                repeatMode = RepeatMode.Reverse
            )
        )

        Box(
            modifier = Modifier
                .size(100.dp)
                .scale(scale)
                .background(Color.Cyan, shape = CircleShape),
            contentAlignment = Alignment.Center
        ) {
            Text("Pulse", color = Color.White, fontSize = 16.sp)
        }
    }

    /* Spring Animation (Bouncy Effect) */
    @Composable
    fun SpringAnimation() {
        var expanded by remember { mutableStateOf(false) }

        val size by animateDpAsState(
            targetValue = if (expanded) 150.dp else 100.dp,
            animationSpec = spring(
                dampingRatio = Spring.DampingRatioMediumBouncy,
                stiffness = Spring.StiffnessLow
            )
        )

        Box(
            modifier = Modifier
                .size(size)
                .background(Color.Yellow, shape = CircleShape)
                .clickable { expanded = !expanded },
            contentAlignment = Alignment.Center
        ) {
            Text("Bounce", color = Color.Black, fontSize = 16.sp)
        }
    }

    /* Advanced Animation (Rotation, Scaling & Color Change on Button) */
    @Composable
    fun AnimatedButton() {
        var clicked by remember { mutableStateOf(false) }

        val rotation by animateFloatAsState(targetValue = if (clicked) 360f else 0f)
        val scale by animateFloatAsState(targetValue = if (clicked) 1.2f else 1f)
        val color by animateColorAsState(targetValue = if (clicked) Color.Green else Color.Red)

        Button(
            onClick = { clicked = !clicked },
            modifier = Modifier
                .graphicsLayer(rotationZ = rotation, scaleX = scale, scaleY = scale)
                .background(color),
        ) {
            Text("Click Me", color = Color.White, fontSize = 16.sp)
        }
    }



    // list

    @Composable
    fun AnimatedListScreen() {
        val itemsList = (1..40).map { "Item $it" }

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .graphicsLayer()
                .padding(16.dp)
        ) {
            items(itemsList) { item ->
                AnimatedListItem(item)
                Spacer(modifier = Modifier.height(8.dp))
            }
        }
    }

    @Composable
    fun AnimatedListItem(text: String) {
        var isVisible by remember { mutableStateOf(false) }

        LaunchedEffect(Unit) {
            isVisible = true
        }

        AnimatedVisibility(
            visible = isVisible,
            enter = fadeIn(animationSpec = tween(500)) + scaleIn(initialScale = 0.8f, animationSpec = tween(500))
        ) {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                elevation = CardDefaults.cardElevation(defaultElevation = 6.dp)
            ) {
                Text(
                    text = text,
                    fontSize = 18.sp,
                    modifier = Modifier.padding(16.dp)
                )
            }
        }
    }


    @Preview(showBackground = true)
    @Composable
    fun PreviewRegisterScreen() {
        AnimatedListScreen()
    }
}


/*

Animation Type            	Function Used

Basic Value Animation	   animate*AsState()
Visibility Animation	    AnimatedVisibility()
Multiple Properties     	updateTransition()
Infinite Animations	     rememberInfiniteTransition()
Physics-Based Motion	    spring(), animateDecay()


* */