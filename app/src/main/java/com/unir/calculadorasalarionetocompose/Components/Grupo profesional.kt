package com.unir.calculadorasalarionetocompose.Components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp



@Composable
fun GrupoProfesional(onValueChange: (String) -> Unit){
    var selectedGrupoProf by remember { mutableStateOf("Grupo Profesional") }
    val grupoProfs = listOf("Licenciados", "Ingenieros técnico, peritos y ayudantes", "Jefes administrativos y de taller", "Ayudantes no titulados", "Oficiales administrativos", "Auxiliares administrativos", "Oficiales de 1ª y 2ª", "Oficiales de 3ª", "Peones", "Trabajadores menores de 18 años", "Autónomos")
    var isSelected by remember { mutableStateOf(false) }
    var expandedContrato by remember { mutableStateOf(false) }
    Column {
        Box(modifier = Modifier
            .fillMaxWidth()
            .height(60.dp)
            .border(BorderStroke(1.dp, MaterialTheme.colorScheme.outline), shape = MaterialTheme.shapes.small)
            .clickable {
                isSelected = !isSelected
                expandedContrato = true },
            contentAlignment = Alignment.CenterStart,
        )
        {
            Text(
                modifier = Modifier
                .padding(start = 10.dp),
                text = selectedGrupoProf,
            )
        }
        if (isSelected) {
            AlertDialog(
                onDismissRequest = { isSelected = false },
                title = { Text(text = "Selecciona tu grupo profesional") },
                confirmButton = {
                    Button(onClick = { isSelected = false }) {
                        Text("Confirmar")
                    }
                },
                dismissButton = {
                    Button(onClick = { isSelected = false }) {
                        Text("Cancelar")
                    }
                },
                text = {
                    Column {
                        grupoProfs.forEach { grupoProf ->
                            Text(
                                text = grupoProf,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .clickable {
                                        selectedGrupoProf =
                                            grupoProf
                                        onValueChange(selectedGrupoProf)
                                        isSelected = false
                                    }
                                    .padding(16.dp)
                            )
                        }
                    }
                }
            )
        }
    }
}