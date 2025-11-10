package ipca.example.storemanagement.itui.register

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

sealed class RegisterState {
    object Idle : RegisterState()
    object Loading : RegisterState()
    object Success : RegisterState()
    data class Error(val message: String) : RegisterState()
}

class RegisterViewModel : ViewModel() {

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

    fun register() {
        viewModelScope.launch {
            if (_email.value.isBlank() || _password.value.isBlank() || _confirmPassword.value.isBlank()) {
                _registerState.value = RegisterState.Error("Todos os campos são obrigatórios.")
                return@launch
            }
            if (_password.value != _confirmPassword.value) {
                _registerState.value = RegisterState.Error("As passwords não coincidem.")
                return@launch
            }

            _registerState.value = RegisterState.Loading
            delay(1500)

            _registerState.value = RegisterState.Success
        }
    }
}
