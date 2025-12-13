package ipca.example.storemanagement.itui.home

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import ipca.example.storemanagement.data.repository.ItemRepositoryImpl
import ipca.example.storemanagement.data.source.local.AppDatabase
import ipca.example.storemanagement.domain.model.ItemModel
import ipca.example.storemanagement.domain.usecase.CreateItemUseCase
import ipca.example.storemanagement.domain.usecase.DeleteItemUseCase
import ipca.example.storemanagement.domain.usecase.GetAllItemsUseCase
import ipca.example.storemanagement.domain.usecase.UpdateItemUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class HomeViewModel(application: Application) : AndroidViewModel(application) {

    //inicializa tudo aqui mesmo
    private val database = AppDatabase.getDatabase(application)
    private val repository = ItemRepositoryImpl(database.itemDao())
    private val getAllItemsUseCase = GetAllItemsUseCase(repository)
    private val createItemUseCase = CreateItemUseCase(repository)
    private val updateItemUseCase = UpdateItemUseCase(repository)
    private val deleteItemUseCase = DeleteItemUseCase(repository)

    private val _items = MutableStateFlow<List<ItemModel>>(emptyList())
    val items = _items.asStateFlow()

    init {
        loadItems()
    }

    private fun loadItems() {
        viewModelScope.launch {
            try {
                getAllItemsUseCase().collect { itemList ->
                    _items.value = itemList
                }
            } catch (e: Exception) {
                Log.e("HomeViewModel", "Erro ao carregar itens: ${e.message}")
            }
        }
    }

    fun createItem(name: String, description: String) {
        viewModelScope.launch {
            try {
                createItemUseCase(name, description)
                Log.d("HomeViewModel", "Item criado com sucesso.")
            } catch (e: Exception) {
                Log.e("HomeViewModel", "Falha ao criar item.", e)
            }
        }
    }

    fun updateItem(item: ItemModel) {
        viewModelScope.launch {
            try {
                updateItemUseCase(item)
                Log.d("HomeViewModel", "Item atualizado com sucesso.")
            } catch (e: Exception) {
                Log.e("HomeViewModel", "Falha ao atualizar item.", e)
            }
        }
    }

    fun deleteItem(itemId: String) {
        viewModelScope.launch {
            try {
                deleteItemUseCase(itemId)
                Log.d("HomeViewModel", "Item deletado com sucesso.")
            } catch (e: Exception) {
                Log.e("HomeViewModel", "Falha ao deletar item.", e)
            }
        }
    }
}