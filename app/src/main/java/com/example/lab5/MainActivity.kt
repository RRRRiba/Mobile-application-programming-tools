package com.example.lab5

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.lab5.ui.theme.Lab5Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Lab5Theme {
                //GalleryApp()
                TipApp()
            }
        }
    }
}

@Composable
fun GalleryApp() {
    data class Slide(val imageRes: Int, val title: String, val artist: String, val year: String)
    var currentSlide by remember { mutableStateOf(0) }
    val slides = listOf(
        Slide(R.drawable.first, "Горный пейзаж", "Иван Петров", "2023"),
        Slide(R.drawable.second, "Морской закат", "Анна Смирнова", "2022"),
        Slide(R.drawable.third, "Лесная тропа", "Сергей Козлов", "2024"),
        Slide(R.drawable.fourth, "Город ночью", "Мария Иванова", "2023")
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Image(
            painter = painterResource(id = slides[currentSlide].imageRes),
            contentDescription = null,
            modifier = Modifier
                .fillMaxWidth()
                .weight(1.5f)
        )

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

@Composable
@Preview(showBackground = true)
fun TipApp() {
    var billAmount by remember { mutableStateOf("") }
    var tipPercentage by remember { mutableStateOf("") }
    var roundUp by remember { mutableStateOf(false) }

    val amount = billAmount.toDoubleOrNull() ?: 0.0
    val percentage = tipPercentage.toDoubleOrNull() ?: 0.0

    var tip by remember(amount, percentage, roundUp) {
        var calculatedTip = percentage / 100 * amount
        if (roundUp) {
            calculatedTip = kotlin.math.ceil(calculatedTip)
        }
        mutableStateOf(calculatedTip)
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(32.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(20.dp)
    ) {
        Text(
            text = "Калькулятор чаевых",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.align(Alignment.Start)
        )

        OutlinedTextField(
            value = billAmount,
            onValueChange = { billAmount = it },
            label = { Text("Сумма счета") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = tipPercentage,
            onValueChange = { tipPercentage = it },
            label = { Text("Процент чаевых") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            modifier = Modifier.fillMaxWidth()
        )

        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text("Округлить до целого?", modifier = Modifier.weight(1f))
            Switch(
                checked = roundUp,
                onCheckedChange = { roundUp = it }
            )
        }

        Surface(
            modifier = Modifier.fillMaxWidth(),
            color = MaterialTheme.colorScheme.primaryContainer,
            shape = MaterialTheme.shapes.medium
        ) {
            Column(
                modifier = Modifier.padding(20.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text("Сумма чаевых:", fontSize = 18.sp)
                Text(
                    text = String.format("%.2f ₽", tip),
                    fontSize = 32.sp,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.primary
                )
            }
        }
    }
}