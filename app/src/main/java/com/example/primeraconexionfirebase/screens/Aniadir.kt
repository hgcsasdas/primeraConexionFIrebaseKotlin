package com.example.primeraconexionfirebase.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.VerticalAlignmentLine
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import com.example.primeraconexionfirebase.navigation.PantallasApp

@Composable
fun Aniadir(navController: NavController){
    Scaffold (
        floatingActionButton = {
            FloatingActionButton(onClick = {
                navController.navigate(route = PantallasApp.FirstScreen.route)
            }) {
                Icon(imageVector = Icons.Filled.ArrowBack, contentDescription = "Crear")
            }
        },
        floatingActionButtonPosition = FabPosition.Center,
        isFloatingActionButtonDocked = true,

)   {
        SecondBodyContent(navController)
    }

}

@Composable
fun SecondBodyContent(navController: NavController){
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Text(text = "Hola")
        Button(onClick = { navController.popBackStack() }) {
            Text(text = "Atrás")
        }
    }
}
