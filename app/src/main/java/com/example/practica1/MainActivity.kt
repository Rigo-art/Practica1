package com.example.peliculas
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
import com.example.peliculas.ui.theme.PeliculasTheme

data class PDay(
    val day: Int,
    val title: String,
    val description: String,
    val imageRes: Int
)

val pDays = listOf(
    PDay(1, "Childs Play", "Childs Play o chucky en los mexicos es una pelicula de culto sobre un muñeco diabolico asesino", R.drawable.chucky),
    PDay(2, "Hereditary", "Una maldicion familiar amenaza la tranquilidad de una familia.", R.drawable.hereditary),
    PDay(3, "Pearl", "Hasta donde te puede llevar tu ambision.", R.drawable.pearl),
    PDay(4, "Megan", "La tecnologia que prometia cuidar a tus hijos se vuelve en tu contra.", R.drawable.megan),
    PDay(5, "Black phone", "Un jooven es secuestrado y solo tiene el apoyo de unos niños que le hablan por telefono que al parecer no son de este mundo.", R.drawable.blackp),
    PDay(6, "Destino final", "Que pasaria si escapas de tu destino?, pues la muerte regresa por ti.", R.drawable.destinof),
    PDay(7, "Jenifer's Body", "Su belleza y su energia sexy es su principal carnada para comer.", R.drawable.diabolicate),
    PDay(8, "Weapons", "A las 3.17 de la mañana todos los niños salieron de su cama y nunca regresaron.", R.drawable.weapons),
    PDay(9, "HIM", "El se sometera a un entrenamiento extremo solo para poder ser el elegido.", R.drawable.him),
    PDay(10, "Gone Girl", "Tu eres la victima?.", R.drawable.goneg),
    PDay(11, "The substance", "Una extraña sustancia hace que tengas una mejor version de ti.", R.drawable.sustancia),
    PDay(12, "Jason X", "Dentro del slasher tambien entra lo absurdo, no se la tomen enserio.", R.drawable.jason),
    PDay(13, "Anaconda", "Un viaje se vuelve su peor pesadilla.", R.drawable.anaconda),
    PDay(14, "Terrifier", "Si tienes el estomago vela.", R.drawable.terrifier),
    PDay(15, "It", "Un ente que se convierte en tus peores pesadillas.", R.drawable.it),
    PDay(16, "Saw", "Los juegos a los que se deben enfrentar son de vida o muerte.", R.drawable.saw),
    PDay(17, "Cujo", "Si, el es mejor amigo del hombre.", R.drawable.cujo),
    PDay(18, "Resident Evil", "No tiene nada que ver con los juegos.", R.drawable.residente),
    PDay(19, "Jaws", "Un tiburon acosa una playa.", R.drawable.jaws),
    PDay(20, "Alien", "Cuando el terror se mezcla con la ciencia ficcion.", R.drawable.alien),
    PDay(21, "Hellraiser", "Un extraño artefacto te lleva atu peor pesadilla.", R.drawable.hellraiser),
    PDay(22, "El Conjuro", "Una obra de arte del terror del señor james guan.", R.drawable.conjuro),
    PDay(23, "La llorona", "La leyenda se hace mas presente y terrorifica en esta pelicula.", R.drawable.llorona),
    PDay(24, "El exorcista", "De verdad ocupas una descripcion?.", R.drawable.exorcis),
    PDay(25, "Scream", "Ghost face tiene muchos planes en esta pelicula.", R.drawable.scream),
    PDay(26, "Mama", "El amor de una madre es incondicional.", R.drawable.mama),
    PDay(27, "El resplandor", "Un hotel, fantasmas, una familia, que puede salir mal?.", R.drawable.resplandor),
    PDay(28, "Midsomar", "Para los amantes del terror folklorico esta es tu pelicula.", R.drawable.midsommar),
    PDay(29, "X", "Es la continuacion de Pearl, aunque esta salio primero.", R.drawable.x),
    PDay(30, "The cabin in the woods", "Sera que todo es planeado, pero por quien?.", R.drawable.lacaba_a)
)

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PeliculasTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    PList(pDays)
                }
            }
        }
    }
}

@Composable
fun PList(days: List<PDay>) {
    LazyColumn(
        modifier = Modifier.padding(8.dp)
    ) {
        items(days) { day ->
            BpCard(day)
        }
    }
}

@Composable
fun BpCard(day: PDay) {
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
                painter = painterResource(id = day.imageRes),
                contentDescription = day.title,
                modifier = Modifier
                    .height(200.dp)
                    .fillMaxWidth(),
                contentScale = ContentScale.Crop
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "Día ${day.day}: ${day.title}",
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.primary
            )
            AnimatedVisibility(
                visible = expanded,
                enter = fadeIn() + expandVertically(),
                exit = fadeOut() + shrinkVertically()
            ) {
                Text(
                    text = day.description,
                    modifier = Modifier.padding(top = 8.dp),
                    color = MaterialTheme.colorScheme.onSurface
                )
            }
        }
    }


}