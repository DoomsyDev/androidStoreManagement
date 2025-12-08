package ipca.example.storemanagement.domain.repository

import ipca.example.storemanagement.domain.model.ItemModel
import kotlinx.coroutines.flow.Flow

interface ItemRepository {
    fun getAllItems(): Flow<List<ItemModel>>
    suspend fun getItemById(id: String): ItemModel?
    suspend fun createItem(item: ItemModel)
    suspend fun updateItem(item: ItemModel)
    suspend fun deleteItem(id: String)
}