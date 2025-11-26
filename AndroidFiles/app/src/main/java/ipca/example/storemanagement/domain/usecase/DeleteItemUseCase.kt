//package ipca.example.storemanagement.domain.usecase
//
//import ipca.example.storemanagement.domain.repository.ItemRepository
//import javax.inject.Inject
//
//class DeleteItemUseCase @Inject constructor(private val itemRepository: ItemRepository) {
//    suspend operator fun invoke(itemId: String): Result<Unit> {
//        return itemRepository.deleteItem(itemId)
//    }
//}
