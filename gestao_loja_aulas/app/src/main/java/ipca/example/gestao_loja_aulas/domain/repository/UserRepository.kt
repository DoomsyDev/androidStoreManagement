package ipca.example.gestao_loja_aulas.domain.repository

import ipca.example.gestao_loja_aulas.domain.model.User
import kotlinx.coroutines.flow.Flow

interface UserRepository {

    fun getCurrentUser(): Flow<User?>

    suspend fun register(email: String, password: String, name: String): User?

    suspend fun login(email: String, password: String): User?

    suspend fun logout()
}
