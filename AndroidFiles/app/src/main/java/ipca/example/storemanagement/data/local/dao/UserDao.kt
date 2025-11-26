//package ipca.example.storemanagement.data.local.dao
//
//import androidx.room.Dao
//import androidx.room.Insert
//import androidx.room.OnConflictStrategy
//import androidx.room.Query
//import ipca.example.storemanagement.data.local.entity.UserEntity
//import kotlinx.coroutines.flow.Flow
//
//@Dao
//interface UserDao {
//    @Insert(onConflict = OnConflictStrategy.REPLACE)
//    suspend fun insertUser(user: UserEntity)
//
//    @Query("SELECT * FROM users WHERE id = :userId LIMIT 1")
//    fun getUserById(userId: String): Flow<UserEntity?>
//
//    @Query("DELETE FROM users")
//    suspend fun clearAll()
//}
