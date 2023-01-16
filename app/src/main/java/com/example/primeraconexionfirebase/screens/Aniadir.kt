package com.example.primeraconexionfirebase.screens

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.primeraconexionfirebase.navigation.PantallasApp
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.launch
import androidx.compose.ui.platform.*
import androidx.compose.ui.text.input.*
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp


@Composable
fun Aniadir(navController: NavController){
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
                Text(text = "Añadir Dragon", modifier = Modifier.padding(10.dp))
            }
        }

)   {
        SecondBodyContent(navController)
    }

}

@Composable
fun SecondBodyContent(navController: NavController){

    val db = FirebaseFirestore.getInstance()

    var nombre_coleccion = "dragones"

    var nombre_dragon by remember { mutableStateOf("") }
    var raza_dragon by remember { mutableStateOf("") }
    var color_dragon by remember { mutableStateOf("") }
    var peso_dragon by remember { mutableStateOf("") }
    var genero_dragon by remember { mutableStateOf("") }
    //var genero_dragon = remember { mutableStateOf("") }
    val gradientColors = listOf(Color(0xFF413846), Color(0xFF807C7C))
    val roundCornerShape = RoundedCornerShape(topEnd = 30.dp, bottomStart = 30.dp)

    /*val genero = listOf("macho", "hembra", "nulo")
    val generoIsSelectedItem: (String) -> Boolean = { genero_dragon.value == it }
    val generoOnChangeState: (String) -> Unit = { genero_dragon.value = it }*/

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.horizontalGradient(colors = gradientColors),
                shape = roundCornerShape
            )
        ,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Text(
            text = "Guardar dragon",
            fontWeight = FontWeight.ExtraBold
        )

        Spacer(modifier = Modifier.size(20.dp))

        OutlinedTextField(
            value = nombre_dragon ,
            onValueChange ={ nombre_dragon  = it },
            label = { Text("Nombre Dragón") },
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
            value = peso_dragon,
            onValueChange = { peso_dragon = it },
            label = { Text("Peso Dragón") },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true,
            //Keyboard = KeyboardType.Number,
        )

        Spacer(modifier = Modifier.size(5.dp))

        OutlinedTextField(
            value = genero_dragon ,
            onValueChange ={ genero_dragon  = it },
            label = { Text("Genero Dragón") },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true,
        )
        /*Text(text = "¿Qué género tiene?: ${genero_dragon.value.ifEmpty { " " }}")
        Spacer(modifier = Modifier.padding(10.dp))
        genero.forEach { item ->
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .selectable(
                        selected = generoIsSelectedItem(item),
                        onClick = { generoOnChangeState(item) },
                        role = Role.RadioButton
                    )
                    .padding(8.dp)
            ) {
                RadioButton(
                    selected = generoIsSelectedItem(item),
                    onClick = null
                )
                Text(
                    text = item,
                    modifier = Modifier.fillMaxWidth()
                )
            }
        }*/
        //Spacer(modifier = Modifier.size(5.dp))

        val dato = hashMapOf(
            "nombre" to nombre_dragon,
            "raza" to raza_dragon,
            "color" to color_dragon,
            "peso" to peso_dragon,
            "genero" to genero_dragon
        )
        val context = LocalContext.current
        Spacer(modifier = Modifier.padding(16.dp))
        Box(
            modifier = Modifier
                .background(
                    brush = Brush.horizontalGradient(listOf(Color(0xFFA8A8A8), Color(0xFF807C7C))),
                    shape = roundCornerShape
                )
                .fillMaxWidth()
                .clip(roundCornerShape)
                .clickable {
                    if (nombre_dragon.isEmpty()) {
                        Toast.makeText(context, "Nombre en blanco", Toast.LENGTH_LONG).show()
                    }else if(raza_dragon.isEmpty()){
                        Toast.makeText(context, "Raza en blanco", Toast.LENGTH_LONG).show()
                    }else if(color_dragon.isEmpty()){
                        Toast.makeText(context, "Color en blanco", Toast.LENGTH_LONG).show()
                    }else if(peso_dragon.isEmpty()){
                        Toast.makeText(context, "Peso en blanco", Toast.LENGTH_LONG).show()
                    }else if(genero_dragon.isEmpty()){
                        Toast.makeText(context, "Genero en blanco", Toast.LENGTH_LONG).show()
                    }else{
                        db.collection(nombre_coleccion)
                            .document(nombre_dragon)
                            .set(dato)
                            .addOnSuccessListener {
                                Toast.makeText(context, "Añadido correctamente", Toast.LENGTH_LONG).show()
                                navController.navigate(route = PantallasApp.Ver.route)
                            }
                            .addOnFailureListener {
                                Toast.makeText(context, "No se ha podido añadir", Toast.LENGTH_LONG).show()
                            }
                        }
                    }
        ) {
            Text(
                text = "Añadir Dragón",
                fontSize = 26.sp,
                color = Color.White,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                modifier = Modifier.size(300.dp, 40.dp)
            )
        }
       /* Button(
            onClick = {
                db.collection(nombre_coleccion)
                    .document(nombre_dragon)
                    .set(dato)
                    .addOnSuccessListener {
                        Toast.makeText(context, "Añadido correctamente", Toast.LENGTH_LONG).show()
                        navController.navigate(route = PantallasApp.Ver.route)
                    }
                    .addOnFailureListener {
                        Toast.makeText(context, "No se ha podido añadir", Toast.LENGTH_LONG).show()
                    }
                }
        ) {
            Text(text = "Añadir Dragón")
        }*/
    }
}