package com.example.lab4

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.lab4.ui.theme.Lab4Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Lab4Theme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                ) {
                    ComposableInfoGrid()
                }
            }
        }
    }
}

@Composable
fun ComposableInfoGrid() {
    Column(Modifier.fillMaxSize()) {
        Row(Modifier.weight(1f)) {
            InfoCard(
                title = "Текст",
                description = "Отображает текстовые элементы с различными стилями и форматами",
                backgroundColor = Color(0xFFE3F2FD),
                Modifier.weight(1f)
            )
            InfoCard(
                title = "Изображение",
                description = "Загружает и отображает изображения из ресурсов приложения",
                backgroundColor = Color(0xFF11F0FD),
                Modifier.weight(1f),
                textColor = Color(0xFFF5F500)
            )
        }
        Row(Modifier.weight(1f)) {
            InfoCard(
                title = "Кнопка",
                description = "Интерактивный элемент для обработки пользовательских действий",
                backgroundColor = Color(0xFFF5F5F5),
                Modifier.weight(1f),
                textColor = Color(0xBBB11050)
            )
            InfoCard(
                title = "Список",
                description = "Отображает прокручиваемый список элементов с одинаковой структурой",
                backgroundColor = Color(0xFFE8F5E9),
                Modifier.weight(1f)
            )
        }
    }
}

@Composable
fun InfoCard(
    title: String,
    description: String,
    backgroundColor: Color,
    modifier: Modifier = Modifier,
    textColor: Color = Color.Black
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(backgroundColor)
            .padding(20.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = title,
            fontWeight = FontWeight.Bold,
            fontSize = 20.sp,
            color = textColor,
            modifier = Modifier.padding(bottom = 12.dp)
        )
        Text(
            text = description,
            textAlign = TextAlign.Center,
            fontSize = 14.sp,
            color = textColor
        )
    }
}

@Preview(showBackground = true)
@Composable
fun ComposableInfoGridPreview() {
    Lab4Theme {
        ComposableInfoGrid()
    }
}