package ipca.example.storemanagement.domain.usecase

import ipca.example.storemanagement.domain.model.ItemModel
import ipca.example.storemanagement.domain.repository.ItemRepository
import kotlinx.coroutines.flow.Flow

class GetAllItemsUseCase(
    private val repository: ItemRepository
) {
    operator fun invoke(): Flow<List<ItemModel>> {
        return repository.getAllItems()
    }
}