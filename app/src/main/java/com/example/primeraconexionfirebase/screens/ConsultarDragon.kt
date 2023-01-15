package com.example.primeraconexionfirebase.screens

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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
//import com.example.primeraconexionfirebase.model.Dragon
import com.example.primeraconexionfirebase.navigation.PantallasApp
import com.google.firebase.firestore.FirebaseFirestore

@Composable
fun ConsultarDragon(navController: NavController) {
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
    ){
        ConsultarUnDragon()
    }
}

@Composable
fun ConsultarUnDragon(){
    var nombre_coleccion = "dragones"
    val db = FirebaseFirestore.getInstance()
    Column(

        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 100.dp)
            .padding(start = 10.dp)
            .padding(end = 10.dp)
    ) {

        Text(
            text = "Búsqueda de dragones por nombre",
            fontWeight = FontWeight.ExtraBold
        )

        Spacer(modifier = Modifier.size(20.dp))

        //DECLARAMOS LA VARIABLE QUE VA A RECOGER LOS DATOS DE LA CONSULTA CON EL ESTADO REMEMBER
        var datos by remember { mutableStateOf("") }
        var nombre_dragon by remember { mutableStateOf("") }
        var color_dragon by remember { mutableStateOf("") }
        var genero_dragon by remember { mutableStateOf("") }
        var peso_dragon by remember { mutableStateOf("") }
        var raza_dragon by remember { mutableStateOf("") }

        var nombre_busqueda by remember { mutableStateOf("") }
        val field_busqueda ="nombre"
        OutlinedTextField(
            value = nombre_busqueda,
            onValueChange = { nombre_busqueda = it },
            label = { Text("Introduce el Dragon a consultar") },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true,
        )

        Spacer(modifier = Modifier.size(5.dp))
        /*var d2 = Dragon()
        var listaDinamica = ArrayList<Dragon>()*/
        Button(
            onClick = {

                // VACIAMOS VARIABLE AL DAR AL BOTON
                datos = ""
                nombre_dragon = ""
                color_dragon = ""
                genero_dragon = ""
                peso_dragon = ""
                raza_dragon = ""
                // HACEMOS LA CONSULTA A LA COLECCION CON GET
                db.collection(nombre_coleccion)
                    .whereEqualTo(field_busqueda,nombre_busqueda)
                    .get()

                    //SI SE CONECTA CORRECTAMENTE
                    // RECORRO TODOS LOS DATOS ENCONTRADOS EN LA COLECCIÓN Y LOS ALMACENO EN DATOS
                    .addOnSuccessListener { resultado ->
                        for (encontrado in resultado) {
                            //Para crear un HashMap con todos los datos
                            datos += "${encontrado.id}: ${encontrado.data}\n"




                            //Para crear un HashMap con todos los datos
                            nombre_dragon += encontrado["nombre"].toString()
                            color_dragon += encontrado["color"].toString()
                            genero_dragon += encontrado["genero"].toString()
                            peso_dragon += encontrado["peso"].toString()
                            raza_dragon += encontrado["raza"].toString()
                            //Log.i("DATOS:", datos)

                            /*d2.setNombreDragon(nombre_dragon)
                            listaDinamica.add(d2)
                            for( i in listaDinamica){
                                println(i.getNombreDragon())
                            }
                            var D1 = Dragon(
                                nombre_dragon,
                                color_dragon,
                                genero_dragon,
                                peso_dragon,
                                raza_dragon
                            );*/
                        }

                        if (datos.isEmpty()) {
                            datos = "No existen datos"
                        }
                    }
                    //SI NO CONECTA CORRECTAMENTE
                    .addOnFailureListener { resultado ->
                        datos = "La conexión a FireStore no se ha podido completar"
                    }
            },

            colors = ButtonDefaults.buttonColors(
                backgroundColor = Color.Blue,
                contentColor = Color.White
            ),
            border = BorderStroke(1.dp, Color.Black)
        )
        {

            Text(text = "Cargar Datos")
        }

        Spacer(modifier = Modifier.size(10.dp))
        // PINTAMOS EL RESULTADO DE LA CONSULTA A LA BASE DE DATOS
        //Text (text = datos)
        Text (text = "Nombre: " + nombre_dragon)
        Text (text = "Raza: " + raza_dragon)
        Text (text = "Genero: " + genero_dragon)
        Text (text = "Color: " + color_dragon)


    }

}