package com.example.primeraconexionfirebase.screens

import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.primeraconexionfirebase.navigation.PantallasApp
import com.google.firebase.firestore.FirebaseFirestore


@Composable
fun Actualizar(navController: NavController){
    Scaffold (
        modifier = Modifier.background(color = Color.LightGray),
        floatingActionButton = {
            FloatingActionButton(onClick = {
                navController.navigate(route = PantallasApp.FirstScreen.route)
            },
                backgroundColor = Color.Gray
            ) {
                Icon(imageVector = Icons.Filled.ArrowBack, contentDescription = "Atrás")
            } },
        isFloatingActionButtonDocked = true,
        bottomBar = {
            BottomAppBar(
                // Defaults to null, that is, No cutout
                cutoutShape = MaterialTheme.shapes.small.copy(
                    CornerSize(percent = 50)
                )
            ) {
                /* Bottom app bar content */
            }
        }

        )   {
        ModificarDragon()
    }
}

@Composable
fun ModificarDragon(){

    val db = FirebaseFirestore.getInstance()

    var nombre_coleccion = "dragones"

    var nombre_dragon by remember { mutableStateOf("") }
    var raza_dragon by remember { mutableStateOf("") }
    var color_dragon by remember { mutableStateOf("") }
    var peso_dragon by remember { mutableStateOf("") }
    var genero_dragon by remember { mutableStateOf("") }
    val context = LocalContext.current

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 100.dp)
            .padding(start= 10.dp)
            .padding(end= 10.dp)
    ) {
        Text(
            text = "Modificar Dragon",
            fontWeight = FontWeight.ExtraBold
        )
        Spacer(modifier = Modifier.size(20.dp))
        OutlinedTextField(
            value = nombre_dragon,
            onValueChange = { nombre_dragon = it },
            label = { Text("Introduce el nombre del Dragón") },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true,
        )

        Spacer(modifier = Modifier.size(5.dp))

        OutlinedTextField(
            value = raza_dragon ,
            onValueChange ={ raza_dragon  = it },
            label = { Text("Raza Dragón") },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true,
        )

        Spacer(modifier = Modifier.size(5.dp))

        OutlinedTextField(
            value = color_dragon ,
            onValueChange ={ color_dragon  = it },
            label = { Text("Color Dragón") },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true,
        )

        Spacer(modifier = Modifier.size(5.dp))

        OutlinedTextField(
            value = peso_dragon ,
            onValueChange ={ peso_dragon  = it },
            label = { Text("Peso Dragón") },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true,
        )

        Spacer(modifier = Modifier.size(5.dp))

        OutlinedTextField(
            value = genero_dragon ,
            onValueChange ={ genero_dragon  = it },
            label = { Text("Genero Dragón") },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true,
        )
        Spacer(modifier = Modifier.size(10.dp))
        val dato = hashMapOf(
            "nombre" to nombre_dragon,
            "raza" to raza_dragon,
            "color" to color_dragon,
            "peso" to peso_dragon,
            "genero" to genero_dragon
        )
        Button(
            onClick = {
                db.collection(nombre_coleccion)
                    .document(nombre_dragon)
                    .set(dato)
                    .addOnSuccessListener {
                        Toast.makeText(context, "Datos actualizados correctamente", Toast.LENGTH_LONG).show()
                        nombre_dragon = ""
                        raza_dragon = ""
                        color_dragon = ""
                        peso_dragon = ""
                        genero_dragon = ""
                    }
                    .addOnFailureListener {
                        Toast.makeText(context, "No se ha podido actualizar", Toast.LENGTH_LONG).show()
                    } },
            colors = ButtonDefaults.buttonColors(
                backgroundColor = Color.Blue,
                contentColor = Color.White
            ),
            border = BorderStroke(1.dp, Color.Black)
        )
        {
            Text(text = "Modificar")
        }
        Spacer(modifier = Modifier.size(5.dp))
    }
}
