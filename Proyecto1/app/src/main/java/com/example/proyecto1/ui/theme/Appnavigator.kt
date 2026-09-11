package com.example.proyecto1.ui.theme

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
@Composable
fun appNavigation() {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = "first_screen"
    ) {
        composable("first_screen") {
            Firstscreen(onNavigateToSecondscreen = { texto ->
                navController.navigate("second_screen/$texto")
            })
        }
        composable(
            route = "second_screen/{texto}",
            arguments = listOf(navArgument("texto") { type = NavType.StringType })
        ) { backStackEntry ->
            val textoRecibido = backStackEntry.arguments?.getString("texto") ?: ""
            Secondscreen(mensaje = textoRecibido)
        }
    }
}