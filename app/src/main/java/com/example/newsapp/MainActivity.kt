package com.example.newsapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
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

// Data class para las noticias
data class Noticia(
    val titulo: String,
    val fecha: String,
    val colorImagen: Color = Color.LightGray
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NewsScreen() {
    var textoBusqueda by remember { mutableStateOf("") }

    val ultimasNoticias = listOf(
        Noticia("El presidente de EE.UU. no muestra signos de arrepentimiento...", "febrero 08 - 2024"),
        Noticia("Banarse en la piscina del desierto de Cleopatra", "febrero 10 - 2024"),
        Noticia("Gigantes tecnologicos invierten en inteligencia artificial", "febrero 12 - 2024")
    )

    val noticiasMundo = listOf(
        Noticia("El presidente de EE.UU. no muestra signos de arrepentimiento...", "", Color(0xFFBDBDBD)),
        Noticia("Banarse en la piscina del desierto de Cleopatra", "", Color(0xFFD4A574)),
        Noticia("Gigantes tecnologicos", "", Color(0xFF90CAF9)),
        Noticia("El rover de Marte envia", "", Color(0xFFFF8A65))
    )

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

        Spacer(modifier = Modifier.height(16.dp))

        // Seccion: Ultimas noticias
        Text(
            text = "Ultimas noticias",
            fontWeight = FontWeight.Bold,
            fontSize = 20.sp
        )

        Spacer(modifier = Modifier.height(8.dp))

        // Carrusel horizontal con LazyRow
        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            items(ultimasNoticias) { noticia ->
                NoticiaDestacadaCard(noticia)
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Seccion: Alrededor del mundo
        Text(
            text = "Alrededor del mundo",
            fontWeight = FontWeight.Bold,
            fontSize = 20.sp
        )

        Spacer(modifier = Modifier.height(8.dp))

        // Grid de 2 columnas con LazyVerticalGrid
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            items(noticiasMundo) { noticia ->
                NewsCard(noticia)
            }
        }
    }
}

// Tarjeta destacada para el carrusel
@Composable
fun NoticiaDestacadaCard(noticia: Noticia) {
    Box(
        modifier = Modifier
            .width(280.dp)
            .height(180.dp)
            .clip(RoundedCornerShape(16.dp))
            .background(Color(0xFF6650a4)),
        contentAlignment = Alignment.BottomStart
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Text(
                text = noticia.titulo,
                color = Color.White,
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = noticia.fecha,
                color = Color.White.copy(alpha = 0.8f),
                fontSize = 14.sp
            )
        }
    }
}

// Tarjeta para el grid de noticias
@Composable
fun NewsCard(noticia: Noticia) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(16.dp))
            .background(Color(0xFFE0E0E0))
    ) {
        Column {
            // Placeholder de imagen
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(120.dp)
                    .background(noticia.colorImagen)
            )
            // Titulo de la noticia
            Text(
                text = noticia.titulo,
                fontSize = 14.sp,
                modifier = Modifier.padding(12.dp)
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
