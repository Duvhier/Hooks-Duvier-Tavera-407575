package com.example.activityresults

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.slideInVertically
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.activityresults.ui.theme.ActivityResultsTheme
import com.example.activityresults.R

class ColorPickerActivity : ComponentActivity() {

    companion object {
        const val RED = 0xFFFF0000L
        const val ORANGE = 0xFFFFA500L
        const val YELLOW = 0xFFFFFF00L
        const val GREEN = 0xFF00FF00L
        const val BLUE = 0xFF0000FFL
        const val INDIGO = 0xFF4B0082L
        const val VIOLET = 0xFF8A2BE2L
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ActivityResultsTheme {
                ColorPickerScreen()
            }
        }
    }

    private fun setRainbowColor(color: Long, colorName: String) {
        Intent().let { pickedColorIntent ->
            pickedColorIntent.putExtra(MainActivity.RAINBOW_COLOR_NAME, colorName)
            pickedColorIntent.putExtra(MainActivity.RAINBOW_COLOR, color)
            setResult(RESULT_OK, pickedColorIntent)
            finish()
        }
    }

    @Composable
    private fun ColorPickerScreen() {
        val gradientBackground = Brush.verticalGradient(
            colors = listOf(
                Color(0xFF3F51B5),
                Color(0xFF5C6BC0),
                Color(0xFF7986CB)
            )
        )

        val poppins = FontFamily(
            Font(R.font.poppins_regular),
            Font(R.font.poppins_bold, FontWeight.Bold)
        )

        val clickHandler = { color: Long, colorName: String ->
            setRainbowColor(color, colorName)
        }

        var visible by remember { mutableStateOf(false) }
        LaunchedEffect(Unit) { visible = true }

        Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
            Column(
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .padding(innerPadding)
                    .fillMaxSize()
                    .background(gradientBackground)
                    .padding(24.dp)
            ) {

                AnimatedVisibility(
                    visible = visible,
                    enter = fadeIn(animationSpec = tween(800)) +
                            slideInVertically(initialOffsetY = { -100 }, animationSpec = tween(800))
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.activityresultsicon),
                        contentDescription = "App Icon",
                        modifier = Modifier
                            .size(120.dp)
                            .padding(bottom = 16.dp)
                    )
                }

                AnimatedVisibility(
                    visible = visible,
                    enter = fadeIn(animationSpec = tween(1000)) +
                            slideInVertically(initialOffsetY = { -80 }, animationSpec = tween(1000))
                ) {
                    Text(
                        text = stringResource(id = R.string.header_text_picker),
                        textAlign = TextAlign.Center,
                        fontSize = 26.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.White,
                        fontFamily = poppins,
                        modifier = Modifier
                            .padding(bottom = 24.dp)
                            .shadow(6.dp, ambientColor = Color.Black)
                    )
                }

                RainbowColor(RED, getString(R.string.red), clickHandler, poppins)
                RainbowColor(ORANGE, getString(R.string.orange), clickHandler, poppins)
                RainbowColor(YELLOW, getString(R.string.yellow), clickHandler, poppins)
                RainbowColor(GREEN, getString(R.string.green), clickHandler, poppins)
                RainbowColor(BLUE, getString(R.string.blue), clickHandler, poppins)
                RainbowColor(INDIGO, getString(R.string.indigo), clickHandler, poppins)
                RainbowColor(VIOLET, getString(R.string.violet), clickHandler, poppins)

                Spacer(modifier = Modifier.height(20.dp))

                Text(
                    text = stringResource(id = R.string.footer_text_picker),
                    textAlign = TextAlign.Center,
                    fontSize = 18.sp,
                    color = Color.White,
                    fontFamily = poppins,
                    modifier = Modifier
                        .padding(top = 16.dp)
                        .fillMaxWidth()
                )

                Button(
                    onClick = { finish() },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 20.dp)
                        .height(50.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF283593))
                ) {
                    Text(
                        text = stringResource(id = R.string.go_back),
                        fontSize = 18.sp,
                        color = Color.White,
                        fontFamily = poppins
                    )
                }
            }
        }
    }

    @Composable
    fun RainbowColor(
        color: Long,
        colorName: String,
        onButtonClick: (Long, String) -> Unit,
        poppins: FontFamily
    ) {
        Button(
            onClick = { onButtonClick(color, colorName) },
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(color),
                contentColor = Color.White
            ),
            modifier = Modifier
                .padding(horizontal = 20.dp, vertical = 4.dp)
                .fillMaxWidth()
                .height(55.dp)
        ) {
            Text(
                text = colorName,
                fontSize = 20.sp,
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.Bold,
                fontFamily = poppins
            )
        }
    }
}
