package ipca.example.gestao_loja_aulas.presentation.user

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import ipca.example.gestao_loja_aulas.domain.model.User
import ipca.example.gestao_loja_aulas.domain.usecase.UserUseCases
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserViewModel @Inject constructor(
    private val useCases: UserUseCases
) : ViewModel() {

    private val _currentUser = MutableStateFlow<User?>(null)
    val currentUser: StateFlow<User?> = _currentUser

    private val _loading = MutableStateFlow(false)
    val loading: StateFlow<Boolean> = _loading

    private val _error = MutableStateFlow<String?>(null)
    val error: StateFlow<String?> = _error

    init {
        loadCurrentUser()
    }

    fun loadCurrentUser() {
        viewModelScope.launch {
            useCases.getCurrentUser().collect { user ->
                _currentUser.value = user
            }
        }
    }

    fun login(
        email: String,
        password: String,
        onSuccess: () -> Unit,
        onError: (String) -> Unit
    ) {
        viewModelScope.launch {
            try {
                useCases.login(email, password)
                loadCurrentUser()
                onSuccess()
            } catch (e: Exception) {
                onError(e.message ?: "Erro desconhecido")
            }
        }
    }

    fun register(
        email: String,
        password: String,
        name: String,
        onSuccess: () -> Unit
    ) {
        viewModelScope.launch {
            try {
                useCases.register(email, password, name)
                loadCurrentUser()
                onSuccess()
            } catch (e: Exception) {
                _error.value = e.message ?: "Erro ao registar"
            }
        }
    }

    fun logout() {
        viewModelScope.launch {
            useCases.logout()
            _currentUser.value = null
        }
    }
}
