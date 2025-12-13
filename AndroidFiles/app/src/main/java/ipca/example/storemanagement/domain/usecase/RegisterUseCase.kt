package ipca.example.storemanagement.domain.usecase

import com.google.firebase.auth.FirebaseAuth
import ipca.example.storemanagement.domain.model.UserModel
import ipca.example.storemanagement.domain.repository.UserRepository
import kotlinx.coroutines.tasks.await

class RegisterUseCase(
    private val repository: UserRepository
) {
    private val auth: FirebaseAuth = FirebaseAuth.getInstance()

    suspend operator fun invoke(name: String, email: String, password: String): Result<UserModel> {
        return try {
            //verifica se existe no room
            val existingUser = repository.getUserByEmail(email)
            if (existingUser != null) {
                return Result.failure(Exception("Email já está em uso"))
            }

            //cria conta firebase
            val authResult = auth.createUserWithEmailAndPassword(email, password).await()
            val firebaseUser = authResult.user

            if (firebaseUser != null) {
                //cria utilizador no room
                val newUser = UserModel(
                    id = firebaseUser.uid,
                    name = name,
                    email = email
                )

                repository.createUser(newUser)
                Result.success(newUser)
            } else {
                Result.failure(Exception("Erro ao criar a conta"))
            }
        } catch (e: Exception) {
            Result.failure(Exception(e.message ?: "Erro desconhecido durante o registo"))
        }
    }
}