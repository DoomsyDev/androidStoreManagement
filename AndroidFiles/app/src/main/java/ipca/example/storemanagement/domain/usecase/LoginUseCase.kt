package ipca.example.storemanagement.domain.usecase

import ipca.example.storemanagement.domain.model.UserModel
import ipca.example.storemanagement.domain.repository.UserRepository

class LoginUseCase(
    private val repository: UserRepository
) {
    suspend operator fun invoke(email: String, password: String): Result<UserModel> {
        return try {
            val user = repository.getUserByEmail(email)
            if (user != null) {
                Result.success(user)
            } else {
                Result.failure(Exception("Usuário não encontrado"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}