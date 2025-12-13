package ipca.example.gestao_loja_aulas.presentation.user

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import ipca.example.gestao_loja_aulas.presentation.navigation.Routes

@Composable
fun RegisterScreen(
    navController: NavController,
    viewModel: UserViewModel = hiltViewModel()
) {
    var name by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var message by remember { mutableStateOf("") }

    Column(modifier = Modifier.padding(16.dp)) {
        TextField(value = name, onValueChange = { name = it }, label = { Text("Nome") })
        Spacer(modifier = Modifier.height(8.dp))
        TextField(value = email, onValueChange = { email = it }, label = { Text("Email") })
        Spacer(modifier = Modifier.height(8.dp))
        TextField(value = password, onValueChange = { password = it }, label = { Text("Password") })
        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = {
            viewModel.register(email, password, name) {
                navController.navigate(Routes.PRODUCTS) {
                    popUpTo(Routes.LOGIN) { inclusive = true }
                }
            }
        }) {
            Text("Registar")
        }

        Spacer(modifier = Modifier.height(8.dp))
        Button(onClick = { navController.navigate(Routes.LOGIN) }) {
            Text("Voltar para Login")
        }

        if (!message.isNullOrEmpty()) {
            Text(message)
        }
    }
}
