package com.example.primeraconexionfirebase.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.VerticalAlignmentLine
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.primeraconexionfirebase.navigation.PantallasApp
import kotlinx.coroutines.launch

@Composable
fun FirstScreen(navController: NavController){
    val scaffoldState = rememberScaffoldState()
    val scope = rememberCoroutineScope()

    Scaffold (
        scaffoldState = scaffoldState,
        topBar = {
            TopBarGeneral(
                onMenuButtonClick = {
                    scope.launch {
                        scaffoldState.drawerState.open()
                    }
                },
                onActionButtonClick = { /* */ }
            )
        },
        drawerContent = { // Contenido del drawer
            DrawerContent { // Cerrar drawer
                scope.launch { scaffoldState.drawerState.close() }
            }
        }

    ){
        BodyContent(navController)
    }
}

@Composable
fun TopBarGeneral(
    onMenuButtonClick: () -> Unit, // Acciones a ejecutar
    onActionButtonClick: (String) -> Unit
) {
        TopAppBar(
            navigationIcon = {
                IconButton(onClick = onMenuButtonClick ) {
                    Icon(imageVector = Icons.Filled.Menu, contentDescription = "Abrir menú desplegable")
                }
            },
            title = { Text(text = "App simple Dragones") },
            actions = {
                IconButton(onClick = { }) {
                    Icon(imageVector = Icons.Filled.Favorite, contentDescription = "Favorito")
                }
                IconButton(onClick = { }) {
                    Icon(imageVector = Icons.Filled.Search, contentDescription = "Buscar")
                }
                IconButton(onClick = { }) {
                    Icon(imageVector = Icons.Filled.MoreVert, contentDescription = "Más")
                }
            }
        )
}

@Composable
fun DrawerContent(closeDrawer: () -> Unit) {
    val sections = listOf(
        "Inicio",
        "Añadir",
        "Editar",
        "Eliminar",
        "Mostrar"
    )
    Column(Modifier.padding(vertical = 8.dp)) {
        sections.forEach { section ->
            TextButton(
                onClick = closeDrawer, // Ejecución
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 8.dp)
            ) {
                Box(
                    modifier = Modifier.fillMaxWidth(),
                    contentAlignment = Alignment.CenterStart
                ) {

                    val textColor = MaterialTheme.colors.onSurface
                    Text(
                        text = section,
                        style = MaterialTheme.typography.body2.copy(color = textColor)
                    )
                }
            }
        }
    }
}
@Composable
fun BodyContent(navController: NavController){
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Text(text = "Hola")
        Button(onClick = {
            navController.navigate(route = PantallasApp.Aniadir.route)
        }) {
            Text(text = "pwepe")
        }
    }
}