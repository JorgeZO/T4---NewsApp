package com.example.newsapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.newsapp.ui.theme.NewsAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NewsAppTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    NewsScreen()
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NewsScreen() {
    var textoBusqueda by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        // Barra de busqueda
        OutlinedTextField(
            value = textoBusqueda,
            onValueChange = { textoBusqueda = it },
            placeholder = { Text("Buscar") },
            leadingIcon = {
                Icon(
                    imageVector = Icons.Default.Search,
                    contentDescription = "Buscar"
                )
            },
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(30.dp),
            singleLine = true
        )

        Spacer(modifier = Modifier.height(12.dp))

        // Tabs: Noticias, Eventos, Clima
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text(
                    text = "Noticias",
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp
                )
                Spacer(modifier = Modifier.height(4.dp))
                Box(
                    modifier = Modifier
                        .width(60.dp)
                        .height(3.dp)
                        .background(
                            Color(0xFF6650a4),
                            shape = RoundedCornerShape(2.dp)
                        )
                )
            }
            Text(
                text = "Eventos",
                fontSize = 16.sp,
                color = Color.Gray
            )
            Text(
                text = "Clima",
                fontSize = 16.sp,
                color = Color.Gray
            )
        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun NewsScreenPreview() {
    NewsAppTheme {
        NewsScreen()
    }
}
