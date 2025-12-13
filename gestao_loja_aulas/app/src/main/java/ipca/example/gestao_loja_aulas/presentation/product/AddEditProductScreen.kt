package ipca.example.gestao_loja_aulas.presentation.product

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
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

    val isEditing = productId.isNotEmpty()

    LaunchedEffect(productId) {
        if (isEditing) {
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

    // SCREEN LAYOUT
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        // TITLE
        Text(
            text = if (isEditing) "Editar Produto" else "Adicionar Produto",
            style = MaterialTheme.typography.headlineSmall
        )

        Spacer(modifier = Modifier.height(20.dp))

        // FORM CARD
        Card(
            modifier = Modifier.fillMaxWidth(0.9f),
            elevation = CardDefaults.cardElevation(4.dp)
        ) {
            Column(modifier = Modifier.padding(16.dp)) {

                TextField(
                    value = name,
                    onValueChange = { name = it },
                    label = { Text("Nome") },
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(12.dp))

                TextField(
                    value = description,
                    onValueChange = { description = it },
                    label = { Text("Descrição") },
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(12.dp))

                TextField(
                    value = price,
                    onValueChange = { price = it },
                    label = { Text("Preço") },
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(12.dp))

                TextField(
                    value = quantity,
                    onValueChange = { quantity = it },
                    label = { Text("Quantidade") },
                    modifier = Modifier.fillMaxWidth()
                )
            }
        }

        Spacer(modifier = Modifier.height(20.dp))

        // ACTION BUTTON
        Button(
            onClick = {
                val product = Product(
                    id = if (isEditing) productId else null,
                    name = name,
                    description = description,
                    price = price.toDoubleOrNull(),
                    quantity = quantity.toIntOrNull()
                )

                if (isEditing) {
                    viewModel.updateProduct(product) { navController.popBackStack() }
                } else {
                    viewModel.addProduct(product) { navController.popBackStack() }
                }
            },
            modifier = Modifier.fillMaxWidth(0.9f)
        ) {
            Text(if (isEditing) "Guardar Alterações" else "Adicionar Produto")
        }
    }
}
