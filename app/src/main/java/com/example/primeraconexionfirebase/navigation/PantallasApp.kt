package com.example.primeraconexionfirebase.navigation

sealed class PantallasApp (val route: String) {
    object FirstScreen: PantallasApp(route = "first_screen")
    object Aniadir: PantallasApp(route = "aniadir_screen")
}