package ipca.example.storemanagement.itui

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseAuth
import ipca.example.storemanagement.data.repository.UserRepositoryImpl
import ipca.example.storemanagement.data.source.local.AppDatabase
import ipca.example.storemanagement.domain.model.UserModel
import ipca.example.storemanagement.domain.usecase.GetUserUseCase
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class UserSessionViewModel(application: Application) : AndroidViewModel(application) {

    private val database = AppDatabase.getDatabase(application)
    private val repository = UserRepositoryImpl(database.userDao())
    private val getUserUseCase = GetUserUseCase(repository)
    private val auth: FirebaseAuth = FirebaseAuth.getInstance()

    private val _currentUser = MutableStateFlow<UserModel?>(null)
    val currentUser = _currentUser.asStateFlow()

    private val _currentUserId = MutableStateFlow<String?>(null)
    val currentUserId = _currentUserId.asStateFlow()

    init {
        //verifica se já existe um utilizador logado no Firebase
        checkExistingSession()
    }

    private fun checkExistingSession() {
        viewModelScope.launch {
            val firebaseUser = auth.currentUser
            if (firebaseUser != null) {
                startUserSession(firebaseUser.uid)
            }
        }
    }


    //inicia uma nova sessão de utilizador e retorna o Job para que se possa esperar por ele.
    fun startUserSession(userId: String): Job {
        _currentUserId.value = userId
        return viewModelScope.launch {
            try {
                val user = getUserUseCase(userId)
                _currentUser.value = user
            } catch (e: Exception) {
                _currentUser.value = null
            }
        }
    }


    //termina a sessão do utilizador.
    fun endUserSession() {
        auth.signOut()  //faz logout tambem do firebase
        _currentUserId.value = null
        _currentUser.value = null
    }
}