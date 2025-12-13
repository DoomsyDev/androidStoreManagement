package ipca.example.gestao_loja_aulas.domain.usecase

import ipca.example.gestao_loja_aulas.domain.usecase.user.GetCurrentUserUseCase
import ipca.example.gestao_loja_aulas.domain.usecase.user.LoginUseCase
import ipca.example.gestao_loja_aulas.domain.usecase.user.LogoutUseCase
import ipca.example.gestao_loja_aulas.domain.usecase.user.RegisterUseCase

data class UserUseCases(
    val login: LoginUseCase,
    val register: RegisterUseCase,
    val logout: LogoutUseCase,
    val getCurrentUser: GetCurrentUserUseCase
)
