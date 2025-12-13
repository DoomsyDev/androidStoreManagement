package ipca.example.storemanagement.data.repository

import ipca.example.storemanagement.data.User
import ipca.example.storemanagement.data.source.local.UserDao
import ipca.example.storemanagement.domain.model.UserModel
import ipca.example.storemanagement.domain.repository.UserRepository

class UserRepositoryImpl(
    private val userDao: UserDao
) : UserRepository {

    override suspend fun getUserByEmail(email: String): UserModel? {
        return userDao.getUserByEmail(email)?.toModel()
    }

    override suspend fun getUserById(id: String): UserModel? {
        return userDao.getUserById(id)?.toModel()
    }

    override suspend fun createUser(user: UserModel) {
        userDao.insertUser(user.toEntity())
    }

    override suspend fun updateUser(user: UserModel) {
        userDao.updateUser(user.toEntity())
    }

    override suspend fun deleteUser(id: String) {
        val user = userDao.getUserById(id)
        user?.let { userDao.deleteUser(it) }
    }

    //mapper extensions
    private fun User.toModel() = UserModel(
        id = id,
        name = name,
        email = email
    )

    private fun UserModel.toEntity() = User(
        id = id,
        name = name,
        email = email
    )
}