package ipca.example.storemanagement.data

import com.google.firebase.database.IgnoreExtraProperties

// Adiciona um construtor vazio e valores padrão para que o Firebase consiga desserializar os dados
@IgnoreExtraProperties // Ignora campos desconhecidos ao ler do Firebase
data class Item(
    val id: String = "", // O ID deve ser uma String para usar as chaves únicas do Firebase
    var name: String = "",
    var description: String = ""
)
