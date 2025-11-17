package ipca.example.storemanagement.itui.home

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import ipca.example.storemanagement.data.Item
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class HomeViewModel : ViewModel() {

    private val _items = MutableStateFlow<List<Item>>(emptyList())
    val items = _items.asStateFlow()

    private val database = FirebaseDatabase.getInstance("https://storemanagementaula-default-rtdb.europe-west1.firebasedatabase.app/").getReference("items")

    init {
        loadItems()
    }

    private fun loadItems() {
        database.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val itemList = mutableListOf<Item>()
                for (itemSnapshot in snapshot.children) {
                    val item = itemSnapshot.getValue(Item::class.java)
                    item?.let { itemList.add(it) }
                }
                _items.value = itemList
            }

            override fun onCancelled(error: DatabaseError) {
                // Tratar o erro, por exemplo, com um log
                Log.e("HomeViewModel", "Erro ao carregar itens: ${error.message}")
            }
        })
    }

    fun createItem(name: String, description: String) {
        // Gera uma chave única para o novo item
        val itemId = database.push().key ?: return

        val newItem = Item(id = itemId, name = name, description = description)

        // Guarda o novo item na base de dados usando a chave gerada
        database.child(itemId).setValue(newItem)
            .addOnSuccessListener {
                Log.d("HomeViewModel", "Item criado com sucesso.")
            }
            .addOnFailureListener {
                Log.e("HomeViewModel", "Falha ao criar item.", it)
            }
    }

    fun updateItem(itemToUpdate: Item) {
        // Atualiza o item na base de dados usando o seu ID
        database.child(itemToUpdate.id).setValue(itemToUpdate)
    }

    fun deleteItem(itemId: String) {
        // Remove o item da base de dados usando o seu ID
        database.child(itemId).removeValue()
    }
}
