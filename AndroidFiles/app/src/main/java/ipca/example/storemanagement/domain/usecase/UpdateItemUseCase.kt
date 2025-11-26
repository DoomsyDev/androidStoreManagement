//package ipca.example.storemanagement.domain.usecase
//
//import ipca.example.storemanagement.domain.model.ItemModel
//import ipca.example.storemanagement.domain.repository.ItemRepository
//import javax.inject.Inject
//
//class UpdateItemUseCase @Inject constructor(private val itemRepository: ItemRepository) {
//    suspend operator fun invoke(item: ItemModel): Result<Unit> {
//        return itemRepository.updateItem(item)
//    }
//}
