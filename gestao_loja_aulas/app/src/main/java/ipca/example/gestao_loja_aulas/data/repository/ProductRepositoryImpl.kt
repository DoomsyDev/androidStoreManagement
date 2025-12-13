package ipca.example.gestao_loja_aulas.data.repository

import ipca.example.gestao_loja_aulas.data.local.ProductDao
import ipca.example.gestao_loja_aulas.data.mapper.toDomain
import ipca.example.gestao_loja_aulas.data.mapper.toEntity
import ipca.example.gestao_loja_aulas.data.remote.FirestoreManager
import ipca.example.gestao_loja_aulas.domain.model.Product
import ipca.example.gestao_loja_aulas.domain.repository.ProductRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class ProductRepositoryImpl @Inject constructor(
    private val dao: ProductDao,
    private val remote: FirestoreManager
) : ProductRepository {

    override fun getProducts(): Flow<List<Product>> =
        dao.getProducts().map { list -> list.map { it.toDomain() } }

    override fun getProductById(id: String): Flow<Product?> =
        dao.getProductById(id).map { it?.toDomain() }

    override suspend fun addProduct(product: Product) {
        remote.addProduct(product)
        dao.insert(product.toEntity())
    }

    override suspend fun updateProduct(product: Product) {
        remote.updateProduct(product)
        dao.update(product.toEntity())
    }

    override suspend fun deleteProduct(id: String) {
        remote.deleteProduct(id)
        dao.delete(id)
    }
}
