package ipca.example.gestao_loja_aulas.presentation.product

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import ipca.example.gestao_loja_aulas.domain.model.Product
import ipca.example.gestao_loja_aulas.domain.usecase.ProductUseCases
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductViewModel @Inject constructor(
    private val useCases: ProductUseCases
) : ViewModel() {

    private val _products = MutableStateFlow<List<Product>>(emptyList())
    val products: StateFlow<List<Product>> = _products

    private val _error = MutableStateFlow<String?>(null)
    val error: StateFlow<String?> = _error

    init {
        observeProducts()
    }

    private fun observeProducts() {
        viewModelScope.launch {
            useCases.getProducts().collectLatest { list ->
                _products.value = list
            }
        }
    }

    fun addProduct(product: Product, onComplete: (() -> Unit)? = null) {
        viewModelScope.launch {
            try {
                useCases.addProduct(product)
                onComplete?.invoke()
            } catch (e: Exception) {
                _error.value = e.message
            }
        }
    }

    fun updateProduct(product: Product, onComplete: (() -> Unit)? = null) {
        viewModelScope.launch {
            try {
                useCases.updateProduct(product)
                onComplete?.invoke()
            } catch (e: Exception) {
                _error.value = e.message
            }
        }
    }

    fun deleteProduct(id: String, onComplete: (() -> Unit)? = null) {
        viewModelScope.launch {
            try {
                useCases.deleteProduct(id)
                onComplete?.invoke()
            } catch (e: Exception) {
                _error.value = e.message
            }
        }
    }

    fun getProductByIdOnce(id: String, onResult: (Product?) -> Unit) {
        viewModelScope.launch {
            useCases.getProductById(id).collectLatest { p ->
                onResult(p)
            }
        }
    }
}
