//package ipca.example.storemanagement.domain.usecase
//
//import ipca.example.storemanagement.domain.model.UserModel
//import ipca.example.storemanagement.domain.repository.AuthRepository
//import kotlinx.coroutines.flow.Flow
//import javax.inject.Inject
//
//class GetCurrentUserUseCase @Inject constructor(private val authRepository: AuthRepository) {
//    operator fun invoke(): Flow<UserModel?> = authRepository.getCurrentUser()
//}
