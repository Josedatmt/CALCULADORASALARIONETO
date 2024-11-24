package com.unir.calculadorasalarionetocompose.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController

@Composable
fun HomeScreen(navController: NavHostController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        // Título
        Text(
            text = "Calculadora de Salario Neto",

            style = MaterialTheme.typography.bodyMedium,
            fontSize = 28.sp
        )
        Spacer(modifier = Modifier.height(32.dp))

        // Botón para Login
        Button(onClick = { navController.navigate("LoginScreen") }) {
            Text("Iniciar Sesión")
        }
        Spacer(modifier = Modifier.height(16.dp))

        // Botón para Registro
        Button(onClick = { navController.navigate("RegisterScreen") }) {
            Text("Registrarse")
        }
    }
}
