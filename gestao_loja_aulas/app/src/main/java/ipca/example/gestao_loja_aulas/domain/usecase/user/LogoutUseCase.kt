package ipca.example.gestao_loja_aulas.domain.usecase.user

import ipca.example.gestao_loja_aulas.domain.repository.UserRepository

class LogoutUseCase(
    private val repository: UserRepository
) {
    suspend operator fun invoke() =
        repository.logout()
}
