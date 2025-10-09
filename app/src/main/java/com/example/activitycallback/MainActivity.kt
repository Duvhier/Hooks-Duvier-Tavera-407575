package com.example.activitycallback

import android.content.res.Configuration
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Assignment
import androidx.compose.material.icons.filled.Build
import androidx.compose.material.icons.filled.CalendarMonth
import androidx.compose.material.icons.filled.Tag
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.activitycallback.ui.theme.ActivityCallbackTheme
class MainActivity : ComponentActivity() {

    companion object {
        private const val TAG = "MainActivity"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ActivityCallbackTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    CenteredText(modifier = Modifier.padding(innerPadding))
                }
            }
        }
        Log.d(TAG, "onCreate")
    }

    override fun onStart() { super.onStart(); Log.d(TAG, "onStart") }
    override fun onResume() { super.onResume(); Log.d(TAG, "onResume") }
    override fun onPause() { super.onPause(); Log.d(TAG, "onPause") }
    override fun onStop() { super.onStop(); Log.d(TAG, "onStop") }
    override fun onRestart() { super.onRestart(); Log.d(TAG, "onRestart") }
    override fun onDestroy() { super.onDestroy(); Log.d(TAG, "onDestroy") }
    override fun onSaveInstanceState(outState: Bundle) { super.onSaveInstanceState(outState); Log.d(TAG, "onSaveInstanceState") }
    override fun onRestoreInstanceState(savedInstanceState: Bundle) { super.onRestoreInstanceState(savedInstanceState); Log.d(TAG, "onRestoreInstanceState") }
    override fun onConfigurationChanged(newConfig: Configuration) { super.onConfigurationChanged(newConfig); Log.d(TAG, "onConfigurationChanged") }
}

@Composable
fun CenteredText(modifier: Modifier = Modifier) {
    val poppins = FontFamily(Font(R.font.poppins_regular))

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(Color(0xFFBBDEFB), Color(0xFFE3F2FD))
                )
            ),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = R.drawable.logcat_logo),
            contentDescription = "LogCat Logo",
            modifier = Modifier
                .size(120.dp)
                .padding(bottom = 24.dp)
        )

        // Nombre
        IconWithText(
            icon = { Icon(Icons.Filled.AccountCircle, contentDescription = "User Icon", tint = Color(0xFF0D47A1)) },
            text = "Duvier Tavera",
            fontFamily = poppins,
            fontSize = 28.sp
        )

        // ID
        IconWithText(
            icon = { Icon(Icons.Filled.Tag, contentDescription = "ID Icon", tint = Color(0xFF1565C0)) },
            text = "ID 407575",
            fontFamily = poppins,
            fontSize = 22.sp
        )

        // Ejercicio
        IconWithText(
            icon = { Icon(Icons.Filled.Assignment, contentDescription = "Exercise Icon", tint = Color(0xFF1976D2)) },
            text = "Exercise 2.01 \n Activity Callback",
            fontFamily = poppins,
            fontSize = 22.sp
        )

        // Hooks
        IconWithText(
            icon = { Icon(Icons.Filled.Build, contentDescription = "Hooks Icon", tint = Color(0xFF1E88E5)) },
            text = "Hooks",
            fontFamily = poppins,
            fontSize = 22.sp
        )

        // Semana
        IconWithText(
            icon = { Icon(Icons.Filled.CalendarMonth, contentDescription = "Week Icon", tint = Color(0xFF2196F3)) },
            text = "Tenth Week",
            fontFamily = poppins,
            fontSize = 22.sp
        )
    }
}

@Composable
fun IconWithText(
    icon: @Composable () -> Unit,
    text: String,
    fontFamily: FontFamily,
    fontSize: androidx.compose.ui.unit.TextUnit
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center,
        modifier = Modifier.padding(vertical = 6.dp)
    ) {
        icon()
        Spacer(modifier = Modifier.width(8.dp))
        Text(
            text = text,
            fontFamily = fontFamily,
            fontSize = fontSize,
            textAlign = TextAlign.Center,
            color = Color.Black
        )
    }
}
