package com.example.saveandrestore

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.core.animateIntAsState
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.saveandrestore.ui.theme.SaveAndRestoreTheme
import kotlin.random.Random

class MainActivity : ComponentActivity() {

    companion object {
        private const val RANDOM_NUMBER = "RANDOM_NUMBER"
    }

    private var randomNumber by mutableStateOf(0)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (savedInstanceState != null) {
            randomNumber = savedInstanceState.getInt(RANDOM_NUMBER, 0)
        }

        setContent {
            SaveAndRestoreTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    RandomNumberScreen(
                        modifier = Modifier.padding(innerPadding),
                        randomNumber = randomNumber,
                        onGenerateClick = { randomNumber = generateRandomNumber() }
                    )
                }
            }
        }
    }

    private fun generateRandomNumber(): Int {
        return Random.nextInt(0, 1000)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt(RANDOM_NUMBER, randomNumber)
    }
}

@Composable
fun RandomNumberScreen(
    modifier: Modifier = Modifier,
    randomNumber: Int,
    onGenerateClick: () -> Unit
) {
    val poppins = FontFamily(Font(R.font.poppins_regular))

    val gradientBrush = Brush.verticalGradient(
        colors = listOf(
            Color(0xFF6A11CB), // Morado
            Color(0xFF2575FC)  // Azul
        )
    )

    val animatedNumber by animateIntAsState(
        targetValue = randomNumber,
        label = "animatedNumber"
    )

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(gradientBrush)
            .padding(24.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Image(
            painter = painterResource(id = R.drawable.randomnumber),
            contentDescription = "Icono de número aleatorio",
            modifier = Modifier
                .size(120.dp)
                .padding(bottom = 16.dp)
        )

        Text(
            text = "Save and Restore",
            fontFamily = poppins,
            fontSize = 28.sp,
            color = Color.White,
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(20.dp))

        Button(onClick = onGenerateClick) {
            Text(
                text = stringResource(id = R.string.generate_random_number),
                fontFamily = poppins,
                fontSize = 18.sp
            )
        }

        Spacer(modifier = Modifier.height(30.dp))

        Text(
            text = stringResource(id = R.string.random_number_message, animatedNumber),
            fontFamily = poppins,
            fontSize = 22.sp,
            color = Color.White,
            textAlign = TextAlign.Center
        )
    }
}
