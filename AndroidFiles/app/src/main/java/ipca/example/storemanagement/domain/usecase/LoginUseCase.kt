package ipca.example.storemanagement.domain.usecase

import com.google.firebase.auth.FirebaseAuth
import ipca.example.storemanagement.domain.model.UserModel
import ipca.example.storemanagement.domain.repository.UserRepository
import kotlinx.coroutines.tasks.await

class LoginUseCase(
    private val repository: UserRepository
) {
    private val auth: FirebaseAuth = FirebaseAuth.getInstance()

    suspend operator fun invoke(email: String, password: String): Result<UserModel> {
        return try {
            //vai autentica fibase
            val authResult = auth.signInWithEmailAndPassword(email, password).await()
            val firebaseUser = authResult.user

            if (firebaseUser != null) {
                //verifica se existe no room
                var user = repository.getUserById(firebaseUser.uid)

                //se nao existir cria
                if (user == null) {
                    val name = email.substringBefore('@')
                        .replaceFirstChar { if (it.isLowerCase()) it.titlecase() else it.toString() }

                    user = UserModel(
                        id = firebaseUser.uid,
                        name = firebaseUser.displayName ?: name,
                        email = firebaseUser.email ?: email
                    )
                    repository.createUser(user)
                }

                Result.success(user)
            } else {
                Result.failure(Exception("Erro ao fazer login"))
            }
        } catch (e: Exception) {
            Result.failure(Exception("Credenciais inválidas: ${e.message}"))
        }
    }
}