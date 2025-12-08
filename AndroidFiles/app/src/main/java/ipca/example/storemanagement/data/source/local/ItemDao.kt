package ipca.example.storemanagement.data.source.local

import androidx.room.*
import ipca.example.storemanagement.data.Item
import kotlinx.coroutines.flow.Flow

@Dao
interface ItemDao {
    @Query("SELECT * FROM items")
    fun getAllItems(): Flow<List<Item>>

    @Query("SELECT * FROM items WHERE id = :id")
    suspend fun getItemById(id: String): Item?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertItem(item: Item)

    @Update
    suspend fun updateItem(item: Item)

    @Delete
    suspend fun deleteItem(item: Item)

    @Query("DELETE FROM items WHERE id = :id")
    suspend fun deleteItemById(id: String)

    @Query("DELETE FROM items")
    suspend fun deleteAllItems()
}