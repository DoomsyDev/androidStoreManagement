package ipca.example.storemanagement.domain.usecase

import ipca.example.storemanagement.domain.model.UserModel
import ipca.example.storemanagement.domain.repository.UserRepository
import java.util.UUID

class RegisterUseCase(
    private val repository: UserRepository
) {
    suspend operator fun invoke(name: String, email: String, password: String): Result<UserModel> {
        return try {
            val existingUser = repository.getUserByEmail(email)
            if (existingUser != null) {
                return Result.failure(Exception("Email já está em uso"))
            }

            val newUser = UserModel(
                id = UUID.randomUUID().toString(),
                name = name,
                email = email
            )

            repository.createUser(newUser)
            Result.success(newUser)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}