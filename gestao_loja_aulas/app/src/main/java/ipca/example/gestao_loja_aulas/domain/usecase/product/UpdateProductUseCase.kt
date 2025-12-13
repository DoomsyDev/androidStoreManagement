package ipca.example.gestao_loja_aulas.domain.usecase.product

import ipca.example.gestao_loja_aulas.domain.model.Product
import ipca.example.gestao_loja_aulas.domain.repository.ProductRepository

class UpdateProductUseCase(
    private val repository: ProductRepository
) {
    suspend operator fun invoke(product: Product) =
        repository.updateProduct(product)
}
