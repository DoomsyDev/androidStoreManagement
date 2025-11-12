package ipca.example.storemanagement.itui.profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseAuth
import ipca.example.storemanagement.data.User
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class ProfileViewModel : ViewModel() {
    private val _user = MutableStateFlow<User?>(null)
    val user = _user.asStateFlow()

    // Instância do Firebase Auth
    private val auth: FirebaseAuth = FirebaseAuth.getInstance()

    init {
        loadUserProfile()
    }

    private fun loadUserProfile() {
        viewModelScope.launch {
            // Ir buscar o utilizador atualmente logado no Firebase
            val firebaseUser = auth.currentUser

            if (firebaseUser != null) {
                // Extrair o nome do e-mail (tudo antes do '@')
                val email = firebaseUser.email ?: "Email não encontrado"
                val name = email.substringBefore('@').replaceFirstChar { if (it.isLowerCase()) it.titlecase() else it.toString() }

                // Criar o nosso objeto User com os dados do Firebase
                _user.value = User(
                    id = firebaseUser.uid,
                    name = name,
                    email = email
                )
            } else {
                // Caso não haja nenhum utilizador logado
                _user.value = null
            }
        }
    }
}
