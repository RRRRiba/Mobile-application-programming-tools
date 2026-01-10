package com.example.gallery

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.gallery.ui.theme.Lab5Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Lab5Theme {
                GalleryApp()
            }
        }
    }
}

@Composable
fun GalleryApp() {
    data class Slide(val imageRes: Int, val title: String, val artist: String, val year: String)
    var currentSlide by remember { mutableStateOf(0) }

    val slides = listOf(
        Slide(com.example.gallery.R.drawable.first, "Энержека", "Энержека Парень", "2025"),
        Slide(com.example.gallery.R.drawable.second, "Я устала...", "Вдова", "2025"),
        Slide(com.example.gallery.R.drawable.third, "Жарко ведь", "Бывший зек", "2025"),
        Slide(com.example.gallery.R.drawable.fourth, "Я точно не гость?", "Кривая", "2025")
    )

    val backgroundImage = painterResource(id = com.example.gallery.R.drawable.background)

    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Image(
            painter = backgroundImage,
            contentDescription = "Фон галереи",
            modifier = Modifier
                .fillMaxSize(),
            alpha = 1.0f
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(20.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1.5f),
                contentAlignment = Alignment.Center
            ) {
                Image(
                    painter = painterResource(id = slides[currentSlide].imageRes),
                    contentDescription = "Картинка слайда",
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight(0.9f)
                )
            }

            Surface(
                modifier = Modifier.fillMaxWidth(),
                color = Color(0xFFF5F5F5),
                shape = MaterialTheme.shapes.medium
            ) {
                Column(
                    modifier = Modifier.padding(20.dp)
                ) {
                    Text(
                        text = slides[currentSlide].title,
                        fontSize = 22.sp,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.primary
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Row {
                        Text(
                            text = slides[currentSlide].artist,
                            fontWeight = FontWeight.SemiBold
                        )
                        Text(text = " (${slides[currentSlide].year})")
                    }
                }
            }

            Surface(
                modifier = Modifier.fillMaxWidth(),
                color = Color.Transparent
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    Button(
                        onClick = {
                            currentSlide = if (currentSlide > 0) currentSlide - 1 else slides.size - 1
                        },
                        modifier = Modifier.weight(1f)
                    ) {
                        Text(text = "Назад")
                    }
                    Button(
                        onClick = {
                            currentSlide = if (currentSlide < slides.size - 1) currentSlide + 1 else 0
                        },
                        modifier = Modifier.weight(1f)
                    ) {
                        Text(text = "Вперед")
                    }
                }
            }
        }
    }
}