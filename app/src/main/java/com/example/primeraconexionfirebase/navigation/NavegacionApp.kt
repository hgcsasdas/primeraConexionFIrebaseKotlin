package com.example.primeraconexionfirebase.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.primeraconexionfirebase.screens.*

@Composable
fun NavegacionApp(){
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = PantallasApp.FirstScreen.route){
        composable(route= PantallasApp.FirstScreen.route){
            FirstScreen(navController)
        }
        composable(route= PantallasApp.Aniadir.route){
            Aniadir(navController)
        }
        composable(route= PantallasApp.Ver.route){
            Ver(navController)
        }
        composable(route= PantallasApp.Actualizar.route){
            Actualizar(navController)
        }
        composable(route= PantallasApp.Eliminar.route){
            Eliminar(navController)
        }
        composable(route= PantallasApp.ConsultarDragon.route){
            ConsultarDragon(navController)
        }
    }
}