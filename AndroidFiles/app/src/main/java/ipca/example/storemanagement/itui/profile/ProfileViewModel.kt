package ipca.example.storemanagement.itui.profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import ipca.example.storemanagement.data.User
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class ProfileViewModel : ViewModel() {
    private val _user = MutableStateFlow<User?>(null)
    val user = _user.asStateFlow()

    init {
        loadUserProfile()
    }

    private fun loadUserProfile() {
        viewModelScope.launch {
            _user.value = User(id = "uid123", name = "Rúben Tech", email = "user@ipca.pt")
        }
    }
}
