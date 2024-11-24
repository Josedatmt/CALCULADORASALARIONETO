package com.unir.calculadorasalarionetocompose.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun SecondScreen(
    contrato: String?,
    discapacidad: String?,
    edad: String?,
    estadoCivil: String?,
    grupoProfesional: String?,
    hijos: String?,
    numPagas: String?,
    salario: String?,
    restaSal: Double? = calcularNeto(salario),  // Se calcula el valor de restaSal aquí
    deduccion: Int = calcularHijos(hijos) + calcularDiscapacidad(discapacidad), // Deducción por hijos y discapacidad
    salNeto: Double? = calcularSalNeto(salario, deduccion, restaSal),  // Se calcula el salario neto aquí
    irpfFinal: Double? = calcularIRPF(salario, salNeto)  // Se calcula el IRPF aquí
) {
    Column (
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        // Resultados calculados
        Text("Salario bruto: $salario"+"€")
        Text("Salario neto: $salNeto" + "€")
        if (salNeto != null) {
            if (numPagas != null) {
                Text("Salario mensual: " + salNeto / numPagas.toInt()+ "€")
            }
        }
        Text("Retención de IRPF: $irpfFinal%")
        Text("Posibles deducciones: $deduccion" +"€")
    }
}

fun calcularNeto(salario: String?): Double? {
    val salarioInt = salario?.toDouble()
    return when {
        salarioInt == null -> null
        salarioInt <= 12500 -> salarioInt * 0.19
        salarioInt <= 20000 -> salarioInt * 0.24
        salarioInt <= 35000 -> salarioInt * 0.30
        salarioInt <= 60000 -> salarioInt * 0.37
        salarioInt <= 290000 -> salarioInt * 0.45
        salarioInt >= 300000 -> salarioInt * 0.47
        else -> null
    }
}

fun calcularHijos(hijos: String?): Int {
    val hijosInt = hijos?.toIntOrNull() ?: 0
    return when (hijosInt) {
        0 -> 0
        1 -> 2000
        2 -> 2500
        3 -> 5000
        else -> 5500
    }
}

fun calcularDiscapacidad(discapacidad: String?): Int {
    return when (discapacidad) {
        "Sin discapacidad" -> 0
        "Mayor o igual al 65%" -> 7000
        "Menor del 65%" -> 4000

        else -> 0
    }
}

fun calcularSalNeto(salario: String?, deduccion: Int, restaSal: Double?): Double? {
    val salInt = salario?.toIntOrNull() ?: return null
    val deduccionFinal = deduccion + (restaSal ?: 0.0).toInt()
    return salInt - deduccionFinal.toDouble()
}

fun calcularIRPF(salario: String?, salNeto: Double?): Double? {
    val salarioInt = salario?.toDoubleOrNull() ?: return null
    val neto = salNeto ?: return null

    if (salarioInt <= 0 || neto <= 0 || neto >= salarioInt) {
        return 0.0
    }

    val irpfAplicado = salarioInt - neto
    return (irpfAplicado / salarioInt) * 100
}