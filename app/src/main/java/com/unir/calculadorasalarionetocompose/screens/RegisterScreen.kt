package com.unir.calculadorasalarionetocompose.screens



import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController

@Composable
fun RegisterScreen(navController: NavHostController) {
    // Usamos remember para almacenar los estados de los campos de entrada
    var username = remember { mutableStateOf("") }
    var password = remember { mutableStateOf("") }
    var confirmPassword = remember { mutableStateOf("") }
    val errorMessage = remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = "Registrarse", style = MaterialTheme.typography.bodyMedium, fontSize = 24.sp)

        Spacer(modifier = Modifier.height(32.dp))

        // Nombre de usuario
        OutlinedTextField(
            value = username.value,
            onValueChange = { username.value = it },
            label = { Text("Usuario") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(16.dp))

        // Contraseña
        OutlinedTextField(
            value = password.value,
            onValueChange = { password.value = it },
            label = { Text("Contraseña") },
            modifier = Modifier.fillMaxWidth(),
            visualTransformation = PasswordVisualTransformation()
        )
        Spacer(modifier = Modifier.height(16.dp))

        // Confirmar contraseña
        OutlinedTextField(
            value = confirmPassword.value,
            onValueChange = { confirmPassword.value = it },
            label = { Text("Confirmar contraseña") },
            modifier = Modifier.fillMaxWidth(),
            visualTransformation = PasswordVisualTransformation()
        )
        Spacer(modifier = Modifier.height(16.dp))

        // Registro
        Button(onClick = {

            if (password.value == confirmPassword.value) {
                navController.navigate("LoginScreen")
            } else {
                errorMessage.value = "Las contraseñas no coinciden"
            }
        }) {
            Text("Registrarse")
        }

        if (errorMessage.value.isNotEmpty()) {

            val context = LocalContext.current
            LaunchedEffect(errorMessage.value) {
                Toast.makeText(context, errorMessage.value, Toast.LENGTH_SHORT).show()
            }

            Text(text = errorMessage.value)
        }

        // Enlace a login
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = {

            navController.navigate("LoginScreen")
        }) {
            Text("Ya tienes cuenta? Inicia sesión")
        }
    }
}
