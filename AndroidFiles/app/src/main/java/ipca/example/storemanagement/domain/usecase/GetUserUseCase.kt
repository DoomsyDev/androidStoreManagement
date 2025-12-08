package ipca.example.storemanagement.domain.usecase

import ipca.example.storemanagement.domain.model.UserModel
import ipca.example.storemanagement.domain.repository.UserRepository

class GetUserUseCase(
    private val repository: UserRepository
) {
    suspend operator fun invoke(userId: String): UserModel? {
        return repository.getUserById(userId)
    }
}