package ipca.example.gestao_loja_aulas.domain.usecase.product

import ipca.example.gestao_loja_aulas.domain.repository.ProductRepository

class DeleteProductUseCase(
    private val repository: ProductRepository
) {
    suspend operator fun invoke(id: String) =
        repository.deleteProduct(id)
}
