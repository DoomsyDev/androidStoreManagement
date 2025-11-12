package ipca.example.storemanagement.itui.profile

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
// Importação importante para usar o viewModel()
import androidx.lifecycle.viewmodel.compose.viewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileScreen(
    // 1. Alterar o ViewModel para ProfileViewModel e inicializá-lo
    profileViewModel: ProfileViewModel = viewModel(),
    onNavigateBack: () -> Unit
) {
    // 2. Obter o estado 'user' a partir do ProfileViewModel correto
    val user by profileViewModel.user.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Perfil do Utilizador") },
                navigationIcon = {
                    IconButton(onClick = onNavigateBack) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Voltar"
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.onPrimaryContainer,
                    navigationIconContentColor = MaterialTheme.colorScheme.onPrimaryContainer
                )
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            // O resto do código já está correto e vai funcionar com a variável 'user'
            user?.let { currentUser ->
                Text(text = "Nome: ${currentUser.name}", style = MaterialTheme.typography.bodyLarge)
                Spacer(modifier = Modifier.height(8.dp))
                Text(text = "Email: ${currentUser.email}", style = MaterialTheme.typography.bodyLarge)
            } ?: run {
                // Esta mensagem será mostrada se o ViewModel ainda não tiver carregado os dados
                // ou se não houver um utilizador logado.
                CircularProgressIndicator() // Mostra um indicador de progresso enquanto carrega
                Spacer(modifier = Modifier.height(16.dp))
                Text(text = "A carregar dados do utilizador...")
            }
        }
    }
}
