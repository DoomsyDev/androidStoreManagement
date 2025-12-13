package ipca.example.gestao_loja_aulas.domain.usecase.product

import ipca.example.gestao_loja_aulas.domain.repository.ProductRepository

class GetProductByIdUseCase(
    private val repository: ProductRepository
) {
    operator fun invoke(id: String) =
        repository.getProductById(id)
}
