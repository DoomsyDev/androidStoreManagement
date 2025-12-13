package ipca.example.storemanagement.data.repository

import ipca.example.storemanagement.data.Item
import ipca.example.storemanagement.data.source.local.ItemDao
import ipca.example.storemanagement.domain.model.ItemModel
import ipca.example.storemanagement.domain.repository.ItemRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class ItemRepositoryImpl(
    private val itemDao: ItemDao
) : ItemRepository {

    override fun getAllItems(): Flow<List<ItemModel>> {
        return itemDao.getAllItems().map { items ->
            items.map { it.toModel() }
        }
    }

    override suspend fun getItemById(id: String): ItemModel? {
        return itemDao.getItemById(id)?.toModel()
    }

    override suspend fun createItem(item: ItemModel) {
        itemDao.insertItem(item.toEntity())
    }

    override suspend fun updateItem(item: ItemModel) {
        itemDao.updateItem(item.toEntity())
    }

    override suspend fun deleteItem(id: String) {
        itemDao.deleteItemById(id)
    }

    //mapper extensions
    private fun Item.toModel() = ItemModel(
        id = id,
        name = name,
        description = description
    )

    private fun ItemModel.toEntity() = Item(
        id = id,
        name = name,
        description = description
    )
}