package ipca.example.gestao_loja_aulas.domain.repository

import ipca.example.gestao_loja_aulas.domain.model.Product
import kotlinx.coroutines.flow.Flow

interface ProductRepository {

    fun getProducts(): Flow<List<Product>>

    fun getProductById(id: String): Flow<Product?>

    suspend fun addProduct(product: Product)

    suspend fun updateProduct(product: Product)

    suspend fun deleteProduct(id: String)
}
