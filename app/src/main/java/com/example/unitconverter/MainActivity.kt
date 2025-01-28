package com.example.unitconverter


import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.unitconverter.ui.theme.UnitConverterTheme
import kotlin.math.roundToInt

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            UnitConverterTheme {
                // A surface container using the 'background' color from the them.
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    UnitConverter()
                }
            }
        }
    }
}



@Composable
fun UnitConverter(){
    var inputValue by remember { mutableStateOf("") }
    var outputValue by remember{ mutableStateOf("") }
    var inputUnit by remember{ mutableStateOf("Meters") }
    var outputUnit by remember{ mutableStateOf("Meters") }
    var iExpanded by remember{ mutableStateOf(false) }
    var oExpanded by remember{ mutableStateOf(false) }
    val iConversionFactor = remember{ mutableStateOf(1.00) }
    val oConversionFactor = remember{ mutableStateOf(1.00) }

    fun ConvertUnits(){
        // --------> ?: --> elvis operator
        val inputValueDouble = inputValue.toDoubleOrNull()?: 0.0
        val result = (inputValueDouble * iConversionFactor.value * 100/oConversionFactor.value).roundToInt()/100
        outputValue = result.toString()

    }
    Column(modifier = Modifier.fillMaxSize(), verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally) {
        // Here goes all the values below each other example :- krishna //
        //                                                      krishna //
        Text(text = "Unit Converter",
            style = MaterialTheme.typography.headlineLarge)
        Spacer(modifier = Modifier.height(16.dp))
        OutlinedTextField(value = inputValue, onValueChange ={
            inputValue = it
            ConvertUnits()
        },
            label = { Text(text = "Enter Value")})
        Spacer(modifier = Modifier.height(16.dp))
        Row {

            // here all the data is written side by side example:-    krishna   krishna //
            // Input Box
            Box {
                // Input Button
                Button(onClick = {iExpanded = true}) {
                    Text(text = inputUnit)
                    Icon(Icons.Default.ArrowDropDown, contentDescription = "Dropdown arrow")
            }
                DropdownMenu(expanded = iExpanded, onDismissRequest = { iExpanded = false }) {
                    DropdownMenuItem(text = {
                        Text(text = "Centimeter") },
                        onClick = {
                            iExpanded = false
                            inputUnit="Centimeter"
                            iConversionFactor.value = 0.01
                            ConvertUnits()
                        })
                    DropdownMenuItem(text = {
                        Text(text = "Meters") },
                        onClick = {
                            iExpanded = false
                            inputUnit="Meters"
                            iConversionFactor.value = 1.0
                            ConvertUnits()
                        })
                    DropdownMenuItem(text = {
                        Text(text = "Feet") },
                        onClick = {
                            iExpanded = false
                            inputUnit="Feet"
                            iConversionFactor.value = 0.3048
                            ConvertUnits()
                        })
                    DropdownMenuItem(text = {
                        Text(text = "Milimeters") },
                        onClick = {
                            iExpanded = false
                            inputUnit="Milimeters"
                            iConversionFactor.value = 0.001
                            ConvertUnits()
                        })
                }
            }
            Spacer(modifier = Modifier.width(16.dp))
            // Output Button
            Box {
                // Output Box
                Button(onClick = { oExpanded = true}) {
                    Text(text = outputUnit)
                    Icon(Icons.Default.ArrowDropDown, contentDescription = "Dropdown arrow")
                }
                DropdownMenu(expanded = oExpanded, onDismissRequest = { oExpanded = false }) {
                    DropdownMenuItem(text = {
                        Text(text = "Centimeter") },
                        onClick = {
                            oExpanded = false
                            outputUnit="Centimeter"
                            oConversionFactor.value = 0.01
                            ConvertUnits()
                        })
                    DropdownMenuItem(text = {
                        Text(text = "Meters") },
                        onClick = {
                            oExpanded = false
                            outputUnit="Meters"
                            oConversionFactor.value = 1.0
                            ConvertUnits()
                        })
                    DropdownMenuItem(text = {
                        Text(text = "Feet") },
                        onClick = {
                            oExpanded = false
                            outputUnit="Feet"
                            oConversionFactor.value = 0.3048
                            ConvertUnits()
                        })
                    DropdownMenuItem(text = {
                        Text(text = "Milimeters") },
                        onClick = {
                            oExpanded = false
                            outputUnit="Milimeters"
                            oConversionFactor.value = 0.001
                            ConvertUnits()
                        })
                }
            }
        }
        Text(text = "Result: "+ outputValue+" "+outputUnit,
            style = MaterialTheme.typography.headlineMedium,
            modifier = Modifier.padding(16.dp))
    }
}

@Preview(showBackground = true)
@Composable
fun UnitConverterPreview(){
    UnitConverter()
}