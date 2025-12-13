package ipca.example.gestao_loja_aulas.presentation.product

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import ipca.example.gestao_loja_aulas.domain.model.Product

@Composable
fun AddEditProductScreen(
    navController: NavController,
    productId: String,
    viewModel: ProductViewModel = hiltViewModel()
) {
    var name by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }
    var price by remember { mutableStateOf("") }
    var quantity by remember { mutableStateOf("") }

    LaunchedEffect(productId) {
        if (productId.isNotEmpty()) {
            viewModel.getProductByIdOnce(productId) { p ->
                p?.let {
                    name = it.name ?: ""
                    description = it.description ?: ""
                    price = it.price?.toString() ?: ""
                    quantity = it.quantity?.toString() ?: ""
                }
            }
        }
    }

    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        TextField(value = name, onValueChange = { name = it }, label = { Text("Nome") })
        Spacer(modifier = Modifier.height(8.dp))
        TextField(value = description, onValueChange = { description = it }, label = { Text("Descrição") })
        Spacer(modifier = Modifier.height(8.dp))
        TextField(value = price, onValueChange = { price = it }, label = { Text("Preço") })
        Spacer(modifier = Modifier.height(8.dp))
        TextField(value = quantity, onValueChange = { quantity = it }, label = { Text("Quantidade") })
        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = {
            val product = Product(
                id = if (productId.isNotEmpty()) productId else null,
                name = name,
                description = description,
                price = price.toDoubleOrNull(),
                quantity = quantity.toIntOrNull()
            )

            if (productId.isNotEmpty()) {
                viewModel.updateProduct(product) {
                    navController.popBackStack()
                }
            } else {
                viewModel.addProduct(product) {
                    navController.popBackStack()
                }
            }
        }) {
            Text(if (productId.isNotEmpty()) "Salvar" else "Adicionar")
        }
    }
}
