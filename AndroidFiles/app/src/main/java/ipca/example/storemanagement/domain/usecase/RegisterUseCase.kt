//package ipca.example.storemanagement.domain.usecase
//
//import android.util.Patterns
//import ipca.example.storemanagement.domain.repository.AuthRepository
//import javax.inject.Inject
//
//class RegisterUseCase @Inject constructor(private val authRepository: AuthRepository) {    suspend operator fun invoke(name: String, email: String, password: String, confirmPassword: String): Result<Unit> {
//    if (name.isBlank()) {
//        return Result.failure(IllegalArgumentException("O nome não pode estar vazio."))
//    }
//    if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
//        return Result.failure(IllegalArgumentException("O formato do email é inválido."))
//    }
//    if (password.length < 6) {
//        return Result.failure(IllegalArgumentException("A password deve ter pelo menos 6 caracteres."))
//    }
//    if (password != confirmPassword) {
//        return Result.failure(IllegalArgumentException("As passwords não coincidem."))
//    }
//    return authRepository.register(name, email, password)
//}
//}
