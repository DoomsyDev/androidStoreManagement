package ipca.example.storemanagement.itui.register

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import ipca.example.storemanagement.data.repository.UserRepositoryImpl
import ipca.example.storemanagement.data.source.local.AppDatabase
import ipca.example.storemanagement.domain.usecase.RegisterUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

sealed class RegisterState {
    data object Idle : RegisterState()
    data object Loading : RegisterState()
    data class Success(val userId: String) : RegisterState()
    data class Error(val message: String) : RegisterState()
}

class RegisterViewModel(application: Application) : AndroidViewModel(application) {

    private val database = AppDatabase.getDatabase(application)
    private val repository = UserRepositoryImpl(database.userDao())
    private val registerUseCase = RegisterUseCase(repository)

    private val _email = MutableStateFlow("")
    val email = _email.asStateFlow()

    private val _password = MutableStateFlow("")
    val password = _password.asStateFlow()

    private val _confirmPassword = MutableStateFlow("")
    val confirmPassword = _confirmPassword.asStateFlow()

    private val _registerState = MutableStateFlow<RegisterState>(RegisterState.Idle)
    val registerState = _registerState.asStateFlow()

    fun onEmailChange(newEmail: String) { _email.value = newEmail }
    fun onPasswordChange(newPassword: String) { _password.value = newPassword }
    fun onConfirmPasswordChange(newConfirm: String) { _confirmPassword.value = newConfirm }

    fun onNavigationHandled() {
        _registerState.value = RegisterState.Idle
    }

    fun register() {
        viewModelScope.launch {
            if (_email.value.isBlank() || _password.value.isBlank() || _confirmPassword.value.isBlank()) {
                _registerState.value = RegisterState.Error("Todos os campos são obrigatórios.")
                return@launch
            }
            if (!android.util.Patterns.EMAIL_ADDRESS.matcher(_email.value).matches()) {
                _registerState.value = RegisterState.Error("O formato do email é inválido.")
                return@launch
            }
            if (_password.value.length < 6) {
                _registerState.value = RegisterState.Error("A password deve ter pelo menos 6 caracteres.")
                return@launch
            }
            if (_password.value != _confirmPassword.value) {
                _registerState.value = RegisterState.Error("As passwords não coincidem.")
                return@launch
            }

            _registerState.value = RegisterState.Loading

            val name = _email.value.substringBefore('@')
                .replaceFirstChar { it.titlecase() }

            val result = registerUseCase(name, _email.value, _password.value)

            result.onSuccess { user ->
                _registerState.value = RegisterState.Success(userId = user.id)
            }.onFailure { exception ->
                _registerState.value = RegisterState.Error(
                    exception.message ?: "Erro desconhecido durante o registo."
                )
            }
        }
    }
}