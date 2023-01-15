package com.example.primeraconexionfirebase.screens

import android.widget.Toast
import androidx.compose.foundation.BorderStroke
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
fun Eliminar(navController: NavController){
    Scaffold (
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
        EliminarDragon()
    }
}

@Composable
fun EliminarDragon(){
    val db = FirebaseFirestore.getInstance()
    val context = LocalContext.current

    var nombre_coleccion = "dragones"
    var nombre_dragon by remember { mutableStateOf("") }




    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 100.dp)
            .padding(start= 10.dp)
            .padding(end= 10.dp)
    ) {
        Text(
            text = "Eliminar Dragon",
            fontWeight = FontWeight.ExtraBold
        )
        Spacer(modifier = Modifier.size(10.dp))
        OutlinedTextField(
            value = nombre_dragon,
            onValueChange = { nombre_dragon = it },
            label = { Text("Introduce el Dragón a borrar") },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true,
        )
        Spacer(modifier = Modifier.size(5.dp))
        Button(
            onClick = {
                if (nombre_dragon.isNotBlank()) {
                    db.collection(nombre_coleccion)
                        .document(nombre_dragon)
                        .delete()
                        .addOnSuccessListener {
                            Toast.makeText(context, "Borrado correctamente", Toast.LENGTH_LONG).show()
                        }
                        .addOnFailureListener {
                            Toast.makeText(context, "No se ha podido borrar", Toast.LENGTH_LONG).show()
                        }
                } },
            colors = ButtonDefaults.buttonColors(
                backgroundColor = Color.Blue,
                contentColor = Color.White
            ),
            border = BorderStroke(1.dp, Color.Black)
        )
        {
            Text(text = "Borrar")
        }
    }
}

