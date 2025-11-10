package ipca.example.storemanagement.itui.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

sealed class LoginState {
    object Idle : LoginState()
    object Loading : LoginState()
    object Success : LoginState()
    data class Error(val message: String) : LoginState()
}

class LoginViewModel : ViewModel() {

    private val _email = MutableStateFlow("")
    val email = _email.asStateFlow()

    private val _password = MutableStateFlow("")
    val password = _password.asStateFlow()

    private val _loginState = MutableStateFlow<LoginState>(LoginState.Idle)
    val loginState = _loginState.asStateFlow()

    fun onEmailChange(newEmail: String) {
        _email.value = newEmail
    }

    fun onPasswordChange(newPassword: String) {
        _password.value = newPassword
    }

    fun login() {
        viewModelScope.launch {
            if (_email.value.isBlank() || _password.value.isBlank()) {
                _loginState.value = LoginState.Error("Email e password são obrigatórios.")
                return@launch
            }

            _loginState.value = LoginState.Loading
            delay(1500)

            if (_email.value == "user@ipca.pt" && _password.value == "1234") {
                _loginState.value = LoginState.Success
            } else {
                _loginState.value = LoginState.Error("Credenciais inválidas.")
            }
        }
    }
}
