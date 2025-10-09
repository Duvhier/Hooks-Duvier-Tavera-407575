package com.example.mainactivity

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.mainactivity.ui.theme.MainActivityTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MainActivityTheme {
                Surface(
                    modifier = Modifier.fillMaxSize()
                ) {
                    MainScreen()
                }
            }
        }
    }
}

@Composable
fun MainScreen() {
    val context = LocalContext.current

    // Fondo con degradado azul a morado
    val gradientBackground = Brush.verticalGradient(
        colors = listOf(
            Color(0xFF3A0CA3), // morado oscuro
            Color(0xFF7209B7), // morado medio
            Color(0xFF4361EE), // azul
            Color(0xFF4CC9F0)  // celeste
        )
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(gradientBackground)
            .padding(24.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Imagen centrada arriba
        Image(
            painter = painterResource(id = R.drawable.standardactivity),
            contentDescription = "App Icon",
            modifier = Modifier
                .size(150.dp)
                .padding(bottom = 24.dp)
        )

        // Título principal
        Text(
            text = "Activity Launch Modes",
            color = Color.White,
            fontSize = 26.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        // Subtítulo
        Text(
            text = "Choose an activity mode to explore",
            color = Color.White.copy(alpha = 0.8f),
            fontSize = 16.sp,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(bottom = 40.dp)
        )

        // Botón Standard
        Button(
            onClick = {
                val intent = Intent(context, StandardActivity::class.java)
                context.startActivity(intent)
            },
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF7209B7)),
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp)
        ) {
            Text("Open Standard Activity", fontSize = 18.sp, color = Color.White)
        }

        // Botón SingleTop
        Button(
            onClick = {
                val intent = Intent(context, SingleTopActivity::class.java)
                context.startActivity(intent)
            },
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF3A0CA3)),
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Open SingleTop Activity", fontSize = 18.sp, color = Color.White)
        }
    }
}
