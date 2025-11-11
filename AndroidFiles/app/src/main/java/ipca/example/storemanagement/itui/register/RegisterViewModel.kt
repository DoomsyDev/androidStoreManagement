package ipca.example.storemanagement.itui.register

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import ipca.example.storemanagement.data.User
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await

sealed class RegisterState {
    object Idle : RegisterState()
    object Loading : RegisterState()
    object Success : RegisterState()
    data class Error(val message: String) : RegisterState()
}

class RegisterViewModel : ViewModel() {

    private val auth: FirebaseAuth = FirebaseAuth.getInstance()
    private val database: FirebaseDatabase = FirebaseDatabase.getInstance()

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
            try {
                // 1. Criar utilizador no Firebase Authentication
                val authResult = auth.createUserWithEmailAndPassword(_email.value, _password.value).await()
                val firebaseUser = authResult.user

                if (firebaseUser != null) {
                    // 2. Guardar informação do utilizador no Realtime Database
                    val name = _email.value.substringBefore('@').replaceFirstChar { it.titlecase() }
                    val user = User(
                        id = firebaseUser.uid,
                        name = name,
                        email = _email.value
                    )
                    // Usamos o UID do utilizador como chave no nó "users"
                    database.getReference("users").child(firebaseUser.uid).setValue(user).await()
                    _registerState.value = RegisterState.Success
                } else {
                    _registerState.value = RegisterState.Error("Ocorreu um erro ao criar a conta.")
                }
            } catch (e: Exception) {
                _registerState.value = RegisterState.Error(e.message ?: "Erro desconhecido durante o registo.")
            }
        }
    }
}
