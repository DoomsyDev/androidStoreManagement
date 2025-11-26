//package ipca.example.storemanagement.domain.usecase
//
//import ipca.example.storemanagement.domain.repository.ItemRepository
//import javax.inject.Inject
//
//class CreateItemUseCase @Inject constructor(private val itemRepository: ItemRepository) {
//    suspend operator fun invoke(name: String, description: String): Result<Unit> {
//        if (name.isBlank()) {
//            return Result.failure(IllegalArgumentException("O nome não pode estar vazio."))
//        }
//        return itemRepository.createItem(name, description)
//    }
//}
