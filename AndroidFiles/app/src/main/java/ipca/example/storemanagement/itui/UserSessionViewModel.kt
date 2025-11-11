package ipca.example.storemanagement.itui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import ipca.example.storemanagement.data.User
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await

class UserSessionViewModel : ViewModel() {

    private val _currentUser = MutableStateFlow<User?>(null)
    val currentUser = _currentUser.asStateFlow()

    private val auth: FirebaseAuth = FirebaseAuth.getInstance()
    private val database: FirebaseDatabase = FirebaseDatabase.getInstance()

    /**
     * Inicia uma nova sessão de utilizador após o login/registo.
     * Busca os dados do utilizador no Realtime Database.
     */
    fun startUserSession() {
        viewModelScope.launch {
            val firebaseUser = auth.currentUser
            if (firebaseUser != null) {
                try {
                    val dataSnapshot = database.getReference("users").child(firebaseUser.uid).get().await()
                    val user = dataSnapshot.getValue(User::class.java)
                    _currentUser.value = user
                } catch (e: Exception) {
                    // Tratar erro, por exemplo, se o utilizador não estiver na base de dados
                    _currentUser.value = null
                }
            }
        }
    }

    /**
     * Termina a sessão do utilizador (logout).
     */
    fun endUserSession() {
        auth.signOut()
        _currentUser.value = null
    }
}
