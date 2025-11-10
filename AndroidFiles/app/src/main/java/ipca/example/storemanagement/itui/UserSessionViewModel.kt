package ipca.example.storemanagement.itui

import androidx.lifecycle.ViewModel
import ipca.example.storemanagement.data.User
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import java.util.UUID

class UserSessionViewModel : ViewModel() {

    private val _currentUser = MutableStateFlow<User?>(null)
    val currentUser = _currentUser.asStateFlow()

    /**
     * Inicia uma nova sessão de utilizador após o login.
     * @param email O email usado para fazer login.
     */
    fun startUserSession(email: String) {
        val name = email.substringBefore('@').replaceFirstChar {
            if (it.isLowerCase()) it.titlecase() else it.toString()
        }

        // Cria um novo objeto User
        _currentUser.value = User(
            id = UUID.randomUUID().toString(),
            name = name,
            email = email
        )
    }

    /**
     * Termina a sessão do utilizador (logout).
     */
    fun endUserSession() {
        _currentUser.value = null
    }
}
