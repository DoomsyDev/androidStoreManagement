package ipca.example.storemanagement.domain.usecase

import ipca.example.storemanagement.domain.model.ItemModel
import ipca.example.storemanagement.domain.repository.ItemRepository
import java.util.UUID

class CreateItemUseCase(
    private val repository: ItemRepository
) {
    suspend operator fun invoke(name: String, description: String) {
        val item = ItemModel(
            id = UUID.randomUUID().toString(),
            name = name,
            description = description
        )
        repository.createItem(item)
    }
}