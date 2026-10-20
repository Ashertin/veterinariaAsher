package com.example.veterinariaasher

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape

import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.veterinariaasher.ui.theme.VeterinariaAsherTheme

// 1. CLASE DE DATOS - Lo que te pide el profe para no repetir código
data class ServicioVet(
    val nombre: String,
    val descripcion: String,
    val emoji: String,
    val color: Color
)

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            VeterinariaAsherTheme {
                AppVeterinaria()
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppVeterinaria() {
    // 2. COLECCIÓN DE DATOS
    val listaServicios = listOf(
        ServicioVet("Consulta General", "Revisión completa y diagnóstico", "🩺", Color(0xFF81C784)),
        ServicioVet("Vacunación", "Carnet de vacunas al día", "💉", Color(0xFF64B5F6)),
        ServicioVet("Peluquería Canina", "Baño, corte y estilo", "✂", Color(0xFFFFB74D)),
        ServicioVet("Cirugía", "Procedimientos seguros", "🏥", Color(0xFFE57373)),
        ServicioVet("Hospitalización", "Cuidado 24/7", "🐾", Color(0xFFBA68C8)),
        ServicioVet("Laboratorio", "Exámenes rápidos", "🔬", Color(0xFF4DB6AC))
    )

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Text("🐾", fontSize = 20.sp) // <-- ANTES ERA Icon(Icons.Filled.Pets)
                        Spacer(Modifier.width(8.dp))
                        Text("VetCare - Pereira", fontWeight = FontWeight.Bold)
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color(0xFF2E7D32),
                    titleContentColor = Color.White
                )
            )
        },
        modifier = Modifier.fillMaxSize()
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            item {
                Card(
                    colors = CardDefaults.cardColors(containerColor = Color(0xFFE8F5E9)),
                    shape = RoundedCornerShape(16.dp),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Column(Modifier.padding(20.dp)) {
                        Text("¡Hola, bienvenido! 👋", fontSize = 22.sp, fontWeight = FontWeight.Bold)
                        Text("¿Cómo está tu peludito hoy?", fontSize = 16.sp, color = Color.Gray)
                        Spacer(Modifier.height(12.dp))
                        Button(
                            onClick = {},
                            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF2E7D32))
                        ) {
                            Button(
                                onClick = {},
                                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF2E7D32))
                            ) {
                                Text("❤️", fontSize = 16.sp)
                                Spacer(Modifier.width(8.dp))
                                Text("Agendar Cita")
                            }
                        }
                    }
                }
            }
            item {
                Text("Nuestros Servicios", fontSize = 20.sp, fontWeight = FontWeight.Bold)
            }
            // 3. RECORRER CON FOR - Evita repetir Cards
            items(listaServicios) { servicio ->
                CardServicio(servicio)
            }
        }
    }
}

@Composable
fun CardServicio(servicio: ServicioVet) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(4.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White)
    ) {
        Row(
            modifier = Modifier.padding(16.dp).fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Card(
                colors = CardDefaults.cardColors(containerColor = servicio.color.copy(alpha = 0.2f)),
                shape = RoundedCornerShape(12.dp)
            ) {
                Text(servicio.emoji, fontSize = 32.sp, modifier = Modifier.padding(12.dp))
            }
            Spacer(Modifier.width(16.dp))
            Column(Modifier.weight(1f)) {
                Text(servicio.nombre, fontWeight = FontWeight.Bold, fontSize = 16.sp)
                Text(servicio.descripcion, fontSize = 13.sp, color = Color.Gray)
            }
            Button(
                onClick = {},
                contentPadding = PaddingValues(horizontal = 12.dp, vertical = 6.dp),
                colors = ButtonDefaults.buttonColors(containerColor = servicio.color)
            ) {
                Text("Ver", fontSize = 12.sp)
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PreviewVet() {
    VeterinariaAsherTheme {
        AppVeterinaria()
    }
}