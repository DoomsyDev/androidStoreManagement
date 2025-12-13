package ipca.example.gestao_loja_aulas.data.repository

import ipca.example.gestao_loja_aulas.data.mapper.toDomain
import ipca.example.gestao_loja_aulas.data.remote.FirebaseAuthManager
import ipca.example.gestao_loja_aulas.data.remote.FirestoreManager
import ipca.example.gestao_loja_aulas.domain.model.User
import ipca.example.gestao_loja_aulas.domain.repository.UserRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val authManager: FirebaseAuthManager,
    private val remote: FirestoreManager
) : UserRepository {


    override fun getCurrentUser(): Flow<User?> = flow {
        val firebaseUser = authManager.currentUser()
        emit(firebaseUser?.toDomain())
    }

    override suspend fun register(
        email: String,
        password: String,
        name: String
    ): User? {
        val result = authManager.register(email, password)
        val firebaseUser = result.user ?: return null

        val newUser = User(
            id = firebaseUser.uid,
            name = name,
            email = email
        )

        remote.saveUser(newUser)

        return newUser
    }

    override suspend fun login(email: String, password: String): User? {
        val result = authManager.login(email, password)
        val firebaseUser = result.user ?: return null

        return remote.getUser(firebaseUser.uid)
    }

    override suspend fun logout() {
        authManager.logout()
    }
}
