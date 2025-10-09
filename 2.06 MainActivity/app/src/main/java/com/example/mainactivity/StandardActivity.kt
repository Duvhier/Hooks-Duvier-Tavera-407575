package com.example.mainactivity

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
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

class StandardActivity : ComponentActivity() {

    companion object {
        var instanceCount = 0
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        instanceCount++ // Incrementamos el contador cada vez que se crea una instancia
        setContent {
            MainActivityTheme {
                Surface(modifier = Modifier.fillMaxSize()) {
                    StandardScreen(instanceCount)
                }
            }
        }
    }
}

@Composable
fun StandardScreen(instanceNumber: Int) {
    val gradientBackground = Brush.verticalGradient(
        colors = listOf(
            Color(0xFF3A0CA3),
            Color(0xFF7209B7),
            Color(0xFF4361EE),
            Color(0xFF4CC9F0)
        )
    )

    val context = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(gradientBackground)
            .padding(24.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = R.drawable.standardactivity),
            contentDescription = "Standard Icon",
            modifier = Modifier
                .size(150.dp)
                .padding(bottom = 24.dp)
        )

        Text(
            text = "Standard Activity",
            color = Color.White,
            fontSize = 26.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        Text(
            text = "Instance number: $instanceNumber",
            color = Color.Yellow,
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        Text(
            text = "Each time you press the button, a new StandardActivity instance is created.",
            color = Color.White.copy(alpha = 0.9f),
            fontSize = 16.sp,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(bottom = 40.dp)
        )

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
            Text("Launch Another Standard Activity", fontSize = 18.sp, color = Color.White)
        }

        Button(
            onClick = { (context as ComponentActivity).finish() },
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF3A0CA3)),
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Go Back", fontSize = 18.sp, color = Color.White)
        }
    }
}
