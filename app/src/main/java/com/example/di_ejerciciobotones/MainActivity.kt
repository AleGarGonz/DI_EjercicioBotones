package com.example.di_ejerciciobotones

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Button
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.di_ejerciciobotones.ui.theme.DI_EjercicioBotonesTheme
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DI_EjercicioBotonesTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    menudeBotones()
                }
            }
        }
    }
}

@Preview
@Composable
fun menudeBotones (){
    Column(Modifier
        .fillMaxSize()) {
    Row(
        Modifier
            .fillMaxWidth()
            .background(Color.Green)){
        Column(Modifier
            .fillMaxWidth(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally){
            botonConTextoPresionado()
        }
    }
        Row(
            Modifier
                .fillMaxWidth()
                .background(Color.Yellow)){
            Column(Modifier
                .fillMaxWidth(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally) {
                botoninvisible()
            }
        }
        Row(
            Modifier
                .fillMaxWidth()
                .background(Color.White)){
            Column(Modifier
                .fillMaxWidth(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally) {
                checkboxTexto()
            }
        }
        Row(
            Modifier
                .fillMaxWidth()
                .background(Color.Cyan)){
            Column(Modifier
                .fillMaxWidth(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally){
                mostrarIcono()
            }
        }
        Row(
            Modifier
                .fillMaxWidth()
                .background(Color.LightGray)){
            Column(Modifier
                .fillMaxWidth(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally){
                botonConRadioBotones()
            }
        }
        Row(
            Modifier
                .fillMaxWidth()
                .background(Color.Gray)){
            Column(Modifier
                .fillMaxHeight()
                .fillMaxWidth(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally) {
                ImageQueCambiaConBoton()
            }
        }
    }
}
@Composable
fun botonConTextoPresionado() {
    var message by remember { mutableStateOf("") }
    var isButtonPressed by remember { mutableStateOf(false) }

    val scope = rememberCoroutineScope()

        Text("Pulsa el botón")

        Button(
            onClick = {
                isButtonPressed = true
                message = "Botón presionado"
                scope.launch {
                    delay(5000L)
                    isButtonPressed = false
                }
            }
        ) {
            Text(text = "Presionar")
        }

        Spacer(modifier = Modifier.height(16.dp))

        if (isButtonPressed) {
            CircularProgressIndicator(
                modifier = Modifier
                    .size(32.dp)
                    .padding(8.dp),
                color = MaterialTheme.colorScheme.primary
            )
        }

        BasicTextField(
            value = message,
            onValueChange = { newMessage ->
                message = newMessage.toString()
            },
            textStyle = TextStyle.Default.copy(fontSize = 24.sp),
            singleLine = true
        )
}
@Composable
fun botoninvisible() {
    var isVisible by remember { mutableStateOf(false) }
    var message by remember { mutableStateOf("") }
    
        Text("Texto invisible")

        Button(onClick = { isVisible = !isVisible }) {
            Text( text= if (isVisible)"Ocultar" else "Mostrar")
        }
        Text(text= if (isVisible)"Texto invisible" else " ")

        if (isVisible) {
            BasicTextField(
                value = message,
                onValueChange = { newMessage ->
                    message = newMessage.toString()
                },
                textStyle = TextStyle.Default.copy(fontSize = 24.sp),
                singleLine = true
            )
        }
}
@Composable
fun checkboxTexto() {
    var showMessage by remember { mutableStateOf(false) }
    var message by remember { mutableStateOf("CheckBox activado") }
        Text("CheckBox")

        Checkbox(
            checked = showMessage,
            onCheckedChange = { showMessage = it }
        )

        Text("Activar")

        if (showMessage) {
            Text(text = message)
        }
}

@Composable
fun mostrarIcono() {
        Text("Icono")

        // Icono de estrella
        Icon(
            painter = painterResource(id = R.drawable.img),
            contentDescription = "Star Icon",
            modifier = Modifier
                .size(48.dp)
                .padding(8.dp)
        )
}
@Composable
fun botonConRadioBotones() {
    var isSwitchChecked by remember { mutableStateOf(false) }
    var selectedOption by remember { mutableStateOf("Option 1") }
    val options = listOf("Option 1", "Option 2", "Option 3")

        Text("Radio Botones")

        Switch(
            checked = isSwitchChecked,
            onCheckedChange = { isChecked -> isSwitchChecked = isChecked },
            modifier = Modifier.padding(8.dp)
        )

        if (isSwitchChecked) {
            Text("Selecciona una opción:")
            options.forEach { option ->
                        Row(
                        modifier = Modifier.clickable { selectedOption = option }
                    ) {
                        RadioButton(
                            selected = selectedOption == option,
                            onClick = { selectedOption = option }
                        )
                        Text(option)
                    }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        Text("Mensaje seleccionado: $selectedOption")
}
@Composable
fun ImageQueCambiaConBoton() {
    val imageList = listOf(
        painterResource(id = R.drawable.perro),
        painterResource(id = R.drawable.waluigi),
        painterResource(id = R.drawable.csm)
    )

    var currentImageIndex by remember { mutableStateOf(0) }

        Text("Cambiar de imagenes")

        Image(
            painter = imageList[currentImageIndex],
            contentDescription = null,
            modifier = Modifier
                .size(120.dp)
                .padding(8.dp)
                .clickable {
                    currentImageIndex = (currentImageIndex + 1) % imageList.size
                }
        )

        Button(
            onClick = {
                currentImageIndex = (currentImageIndex + 1) % imageList.size
            }
        ) {
            Text(text = "Cambiar Imagen")
        }
}