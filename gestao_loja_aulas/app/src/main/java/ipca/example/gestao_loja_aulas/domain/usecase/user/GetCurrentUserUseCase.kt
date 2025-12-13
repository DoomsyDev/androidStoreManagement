package ipca.example.gestao_loja_aulas.domain.usecase.user

import ipca.example.gestao_loja_aulas.domain.model.User
import ipca.example.gestao_loja_aulas.domain.repository.UserRepository
import kotlinx.coroutines.flow.Flow

class GetCurrentUserUseCase(
    private val repository: UserRepository
) {
    operator fun invoke(): Flow<User?> =
        repository.getCurrentUser()
}
