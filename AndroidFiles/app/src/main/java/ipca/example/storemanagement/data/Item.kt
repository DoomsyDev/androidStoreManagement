package ipca.example.storemanagement.data

import com.google.firebase.database.IgnoreExtraProperties

@IgnoreExtraProperties
data class Item(
    val id: String = "",
    var name: String = "",
    var description: String = ""
)
