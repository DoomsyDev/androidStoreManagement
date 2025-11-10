package ipca.example.storemanagement.data

// 'data class' é ideal para guardar estado
data class Item(
    val id: Int,
    var name: String,
    var description: String
)
