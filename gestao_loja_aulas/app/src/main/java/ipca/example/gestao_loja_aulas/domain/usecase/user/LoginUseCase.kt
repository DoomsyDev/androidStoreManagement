package ipca.example.gestao_loja_aulas.domain.usecase.user

import ipca.example.gestao_loja_aulas.domain.model.User
import ipca.example.gestao_loja_aulas.domain.repository.UserRepository

class LoginUseCase(
    private val repository: UserRepository
) {
    suspend operator fun invoke(
        email: String,
        password: String
    ): User? {
        return repository.login(email, password)
    }
}
