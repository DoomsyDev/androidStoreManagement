package ipca.example.storemanagement.itui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import ipca.example.storemanagement.data.Item // Importar o Item da pasta 'data'
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class HomeViewModel : ViewModel() {

    private val _items = MutableStateFlow<List<Item>>(emptyList())
    val items = _items.asStateFlow()

    init {
        loadItems()
    }

    private fun loadItems() {
        viewModelScope.launch {
            // Simulação: Substituir por uma chamada a uma base de dados ou API
            _items.value = listOf(
                Item(1, "Portátil", "i7, 16GB RAM"),
                Item(2, "Rato", "Sem fios, ergonómico"),
                Item(3, "Monitor", "27 polegadas, 4K")
            )
        }
    }

    fun createItem(name: String, description: String) {
        val newItem = Item(
            id = (_items.value.maxOfOrNull { it.id } ?: 0) + 1,
            name = name,
            description = description
        )
        _items.update { currentList -> currentList + newItem }
    }

    fun updateItem(itemToUpdate: Item) {
        _items.update { currentList ->
            currentList.map { if (it.id == itemToUpdate.id) itemToUpdate else it }
        }
    }

    fun deleteItem(itemId: Int) {
        _items.update { currentList ->
            currentList.filterNot { it.id == itemId }
        }
    }
}
