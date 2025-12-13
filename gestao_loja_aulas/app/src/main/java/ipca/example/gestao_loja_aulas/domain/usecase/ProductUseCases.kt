package ipca.example.gestao_loja_aulas.domain.usecase

import ipca.example.gestao_loja_aulas.domain.usecase.product.AddProductUseCase
import ipca.example.gestao_loja_aulas.domain.usecase.product.DeleteProductUseCase
import ipca.example.gestao_loja_aulas.domain.usecase.product.GetProductByIdUseCase
import ipca.example.gestao_loja_aulas.domain.usecase.product.GetProductsUseCase
import ipca.example.gestao_loja_aulas.domain.usecase.product.UpdateProductUseCase

data class ProductUseCases(
    val addProduct: AddProductUseCase,
    val getProducts: GetProductsUseCase,
    val getProductById: GetProductByIdUseCase,
    val updateProduct: UpdateProductUseCase,
    val deleteProduct: DeleteProductUseCase
)
