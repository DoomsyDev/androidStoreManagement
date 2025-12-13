package ipca.example.gestao_loja_aulas.data.remote

import com.google.firebase.firestore.FirebaseFirestore
import ipca.example.gestao_loja_aulas.domain.model.Product
import ipca.example.gestao_loja_aulas.domain.model.User
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class FirestoreManager @Inject constructor(
    private val db: FirebaseFirestore
) {

    private val users = db.collection("users")
    private val products = db.collection("products")


    // USER -----------------------------------------------------

    suspend fun saveUser(user: User) {
        user.id?.let {
            users.document(it).set(user).await()
        }
    }

    suspend fun getUser(id: String): User? =
        users.document(id).get().await().toObject(User::class.java)


    // PRODUCT --------------------------------------------------

    suspend fun addProduct(product: Product) {
        val id = product.id ?: products.document().id
        product.id = id
        products.document(id).set(product).await()
    }

    suspend fun updateProduct(product: Product) {
        product.id?.let { products.document(it).set(product).await() }
    }

    suspend fun deleteProduct(id: String) {
        products.document(id).delete().await()
    }

    fun getProducts() = products

    fun getProduct(id: String) = products.document(id)
}
