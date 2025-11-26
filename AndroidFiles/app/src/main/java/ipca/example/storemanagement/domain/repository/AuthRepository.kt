package ipca.example.storemanagement.domain.repository

import ipca.example.storemanagement.domain.model.UserModel
import kotlinx.coroutines.flow.Flow

interface AuthRepository {
    suspend fun login(email: String, password: String): Result<Unit>
    suspend fun register(name: String, email: String, password: String): Result<Unit>
    suspend fun logout()
    fun getCurrentUser(): Flow<UserModel?>
    suspend fun getCurrentUserId(): String?
}
