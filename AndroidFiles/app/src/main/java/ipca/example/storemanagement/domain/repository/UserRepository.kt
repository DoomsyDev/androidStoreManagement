package ipca.example.storemanagement.domain.repository

import ipca.example.storemanagement.domain.model.UserModel

interface UserRepository {
    suspend fun getUserByEmail(email: String): UserModel?
    suspend fun getUserById(id: String): UserModel?
    suspend fun createUser(user: UserModel)
    suspend fun updateUser(user: UserModel)
    suspend fun deleteUser(id: String)
}