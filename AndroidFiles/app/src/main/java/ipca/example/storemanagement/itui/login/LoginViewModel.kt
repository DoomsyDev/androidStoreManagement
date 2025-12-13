package ipca.example.storemanagement.itui.login

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import ipca.example.storemanagement.data.repository.UserRepositoryImpl
import ipca.example.storemanagement.data.source.local.AppDatabase
import ipca.example.storemanagement.domain.usecase.LoginUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

sealed class LoginState {
    object Idle : LoginState()
    object Loading : LoginState()
    data class Success(val userId: String) : LoginState()
    data class Error(val message: String) : LoginState()
}

class LoginViewModel(application: Application) : AndroidViewModel(application) {

    private val database = AppDatabase.getDatabase(application)
    private val repository = UserRepositoryImpl(database.userDao())
    private val loginUseCase = LoginUseCase(repository)

    private val _email = MutableStateFlow("")
    val email = _email.asStateFlow()

    private val _password = MutableStateFlow("")
    val password = _password.asStateFlow()

    private val _loginState = MutableStateFlow<LoginState>(LoginState.Idle)
    val loginState = _loginState.asStateFlow()

    fun onEmailChange(newEmail: String) {
        _email.value = newEmail
        if (_loginState.value is LoginState.Error) {
            _loginState.value = LoginState.Idle
        }
    }

    fun onPasswordChange(newPassword: String) {
        _password.value = newPassword
        if (_loginState.value is LoginState.Error) {
            _loginState.value = LoginState.Idle
        }
    }

    fun login() {
        viewModelScope.launch {
            if (_email.value.isBlank() || _password.value.isBlank()) {
                _loginState.value = LoginState.Error("Email e password são obrigatórios.")
                return@launch
            }

            _loginState.value = LoginState.Loading

            val result = loginUseCase(_email.value, _password.value)

            result.onSuccess { user ->
                _loginState.value = LoginState.Success(user.id)
            }.onFailure { exception ->
                _loginState.value = LoginState.Error(
                    exception.message ?: "Credenciais inválidas."
                )
            }
        }
    }
}