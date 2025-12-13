package ipca.example.gestao_loja_aulas.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "products")
data class ProductEntity(
    @PrimaryKey(autoGenerate = false)
    val id: String,
    val name: String?,
    val description: String?,
    val price: Double?,
    val quantity: Int?
)
