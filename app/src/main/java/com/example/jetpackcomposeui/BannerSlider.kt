package com.example.jetpackcomposeui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.CachePolicy
import coil.request.ImageRequest
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class BannerSlider : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BannerWithIndicator()
        }
    }
}

@Composable
fun BannerWithIndicator() {
    val images = listOf(
        "https://picsum.photos/600/300",  // ✅ Smaller size for fast loading
        "https://placekitten.com/600/300",
        "https://via.placeholder.com/600x300.png",
        "https://source.unsplash.com/random/600x300?landscape"
    )

    val pagerState = rememberPagerState(pageCount = { images.size })
    val coroutineScope = rememberCoroutineScope()

    // ✅ Auto-scroll every 3 seconds
    LaunchedEffect(Unit) {
        while (true) {
            delay(3000) // Auto-scroll interval
            coroutineScope.launch {
                val nextPage = (pagerState.currentPage + 1) % images.size
                pagerState.animateScrollToPage(nextPage)
            }
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White) // ✅ Background Color
    ) {
        // ✅ ViewPager (Top of the Screen)
        HorizontalPager(
            state = pagerState,
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp) // Set Banner Height
        ) { page ->
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(images[page])
                    .diskCachePolicy(CachePolicy.ENABLED) // ✅ Faster Loading with Caching
                    .crossfade(true) // ✅ Smooth Transition
                    .build(),
                contentDescription = "Banner Image",
                modifier = Modifier.fillMaxSize(),
                placeholder = painterResource(R.drawable.ic_launcher_foreground), // ✅ Temporary image while loading
                error = painterResource(R.drawable.ic_launcher_foreground) // ✅ Error image if URL fails
            )
        }

        // ✅ Dots Indicator Below ViewPager
        Row(
            Modifier
                .fillMaxWidth()
                .padding(top = 8.dp),
            horizontalArrangement = Arrangement.Center
        ) {
            repeat(images.size) { index ->
                Box(
                    modifier = Modifier
                        .padding(4.dp)
                        .size(if (pagerState.currentPage == index) 10.dp else 6.dp)
                        .background(
                            if (pagerState.currentPage == index) MaterialTheme.colorScheme.primary
                            else Color.Gray,
                            shape = MaterialTheme.shapes.small
                        )
                )
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // ✅ Other Content Below Banner
        Text(
            text = "Welcome to Jetpack Compose",
            style = MaterialTheme.typography.headlineMedium,
            modifier = Modifier.padding(16.dp)
        )

        Text(
            text = "This is a banner slider at the top with auto-scroll and indicators.",
            style = MaterialTheme.typography.bodyLarge,
            modifier = Modifier.padding(16.dp)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewBannerSlider() {
    BannerWithIndicator()
}
