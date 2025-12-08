package ipca.example.storemanagement.domain.usecase

import ipca.example.storemanagement.domain.repository.ItemRepository

class DeleteItemUseCase(
    private val repository: ItemRepository
) {
    suspend operator fun invoke(itemId: String) {
        repository.deleteItem(itemId)
    }
}