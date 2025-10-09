package com.example.activityresults

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
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

class MainActivity : ComponentActivity() {

    companion object {
        const val RAINBOW_COLOR_NAME = "RAINBOW_COLOR_NAME"
        const val RAINBOW_COLOR = "RAINBOW_COLOR"
        const val TRANSPARENT = 0x00FFFFFFL
    }

    private var rainbowColor by mutableStateOf(Color(TRANSPARENT))
    private var colorName by mutableStateOf("")
    private var colorMessage by mutableStateOf("")

    private val startForResult =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { activityResult ->
            val data = activityResult.data
            rainbowColor = Color(data?.getLongExtra(RAINBOW_COLOR, TRANSPARENT) ?: TRANSPARENT)
            colorName = data?.getStringExtra(RAINBOW_COLOR_NAME) ?: ""
            colorMessage = getString(R.string.color_chosen_message, colorName)
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val context = LocalContext.current
            ActivityResultsTheme {
                MainScreen(rainbowColor, colorMessage, context, startForResult)
            }
        }
    }
}

@Composable
fun TextWithBackgroundColor(backgroundColor: Color, colorMessage: String) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .padding(horizontal = 20.dp)
            .height(60.dp)
            .fillMaxWidth()
            .background(backgroundColor)
    ) {
        Text(
            text = if (colorMessage.isNotEmpty()) colorMessage else "",
            fontSize = 20.sp,
            color = Color.White,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(8.dp)
        )
    }
}

@Composable
fun MainScreen(
    backgroundColor: Color,
    colorMessage: String,
    context: Context,
    startForResult: ActivityResultLauncher<Intent>
) {
    val gradientBrush = Brush.verticalGradient(
        colors = listOf(
            Color(0xFF3F51B5),
            Color(0xFF7986CB),
            Color(0xFF9FA8DA)
        )
    )

    val poppins = FontFamily(
        Font(R.font.poppins_regular),
        Font(R.font.poppins_bold, FontWeight.Bold)
    )

    var visible by remember { mutableStateOf(false) }
    LaunchedEffect(Unit) { visible = true }

    Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
                .background(gradientBrush)
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
                        .padding(bottom = 24.dp)
                )
            }

            AnimatedVisibility(
                visible = visible,
                enter = fadeIn(animationSpec = tween(1000)) +
                        slideInVertically(initialOffsetY = { -80 }, animationSpec = tween(1000))
            ) {
                Text(
                    text = stringResource(id = R.string.header_text_main),
                    fontSize = 26.sp,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center,
                    color = Color.White,
                    fontFamily = poppins,
                    modifier = Modifier
                        .padding(bottom = 40.dp)
                        .shadow(8.dp, ambientColor = Color.Black)
                )
            }

            Button(
                onClick = {
                    val intent = Intent(context, ColorPickerActivity::class.java)
                    startForResult.launch(intent)
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(60.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF283593))
            ) {
                Text(
                    text = stringResource(id = R.string.submit_button_text),
                    fontSize = 18.sp,
                    color = Color.White,
                    fontFamily = poppins
                )
            }

            Spacer(modifier = Modifier.height(40.dp))

            TextWithBackgroundColor(backgroundColor, colorMessage)
        }
    }
}
