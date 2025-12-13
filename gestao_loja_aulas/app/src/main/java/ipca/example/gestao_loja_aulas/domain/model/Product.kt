package ipca.example.gestao_loja_aulas.domain.model

data class Product(
    var id: String? = null,
    var name: String? = null,
    var description: String? = null,
    var price: Double? = null,
    var quantity: Int? = null
)