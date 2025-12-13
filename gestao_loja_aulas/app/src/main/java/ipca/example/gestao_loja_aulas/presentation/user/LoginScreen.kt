package ipca.example.gestao_loja_aulas.presentation.user

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import ipca.example.gestao_loja_aulas.presentation.navigation.Routes

@Composable
fun LoginScreen(
    navController: NavController,
    viewModel: UserViewModel = hiltViewModel()
) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var error by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TextField(
            value = email,
            onValueChange = { email = it },
            label = { Text("Email") },
            modifier = Modifier.fillMaxWidth(0.9f)
        )
        Spacer(modifier = Modifier.height(8.dp))

        TextField(
            value = password,
            onValueChange = { password = it },
            label = { Text("Password") },
            modifier = Modifier.fillMaxWidth(0.9f)
        )
        Spacer(modifier = Modifier.height(12.dp))

        Button(
            onClick = {
                viewModel.login(
                    email, password,
                    onSuccess = { navController.navigate(Routes.PRODUCTS) },
                    onError = { error = it }
                )
            },
            modifier = Modifier.fillMaxWidth(0.9f)
        ) {
            Text("Login")
        }

        Spacer(modifier = Modifier.height(12.dp))

        Text(
            text = "Não tens conta? Regista-te aqui",
            modifier = Modifier
                .clickable {
                    navController.navigate(Routes.REGISTER)
                }
                .padding(8.dp)
        )

        if (error.isNotEmpty()) {
            Spacer(modifier = Modifier.height(12.dp))
            Text(error, color = MaterialTheme.colorScheme.error)
        }
    }
}
