package com.example.practica1
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.practica1.ui.theme.Practica1Theme

data class Equipo(
    val equip: Int,
    val title: String,
    val description: String,
    val imageRes: Int
)

val nEquip = listOf(
    Equipo(1, "Computadora", "Equipo principal", R.drawable.chucky),
    Equipo(2, "Mouse", "Periferico que permite acceso al la computadora", R.drawable.chucky),
    Equipo(3, "HardWare", "Equipo principal del sistema", R.drawable.chucky)
)

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Practica1Theme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    PList(nEquip)
                }
            }
        }
    }
}

@Composable
fun PList(equipo: List<Equipo>) {
    LazyColumn(
        modifier = Modifier.padding(8.dp)
    ) {
        items(equipo) { equipo ->
            BpCard(equipo)
        }
    }
}

@Composable
fun BpCard(equipo: Equipo) {
    var expanded by remember { mutableStateOf(false) }


    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
            .clickable { expanded = !expanded },
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant),
        elevation = CardDefaults.cardElevation(6.dp)
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.padding(16.dp)
        ) {
            Image(
                painter = painterResource(id = equipo.imageRes),
                contentDescription = equipo.title,
                modifier = Modifier
                    .height(200.dp)
                    .fillMaxWidth(),
                contentScale = ContentScale.Crop
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "Equipo ${equipo.equip}: ${equipo.title}",
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.primary
            )
            AnimatedVisibility(
                visible = expanded,
                enter = fadeIn() + expandVertically(),
                exit = fadeOut() + shrinkVertically()
            ) {
                Text(
                    text = equipo.description,
                    modifier = Modifier.padding(top = 8.dp),
                    color = MaterialTheme.colorScheme.onSurface
                )
            }
        }
    }


}