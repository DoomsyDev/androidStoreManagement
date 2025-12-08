package ipca.example.storemanagement.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.firebase.database.IgnoreExtraProperties

@IgnoreExtraProperties
@Entity(tableName = "items")
data class Item(
    @PrimaryKey
    val id: String = "",
    var name: String = "",
    var description: String = ""
)