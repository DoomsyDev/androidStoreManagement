package ipca.example.gestao_loja_aulas.presentation.product

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import ipca.example.gestao_loja_aulas.domain.model.Product
import ipca.example.gestao_loja_aulas.presentation.navigation.Routes

@Composable
fun ProductListScreen(
    navController: NavController,
    viewModel: ProductViewModel = hiltViewModel()
) {
    val products by viewModel.products.collectAsState()

    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
            Text("Produtos")
            Button(onClick = { navController.navigate("${Routes.ADD_EDIT_PRODUCT}") }) {
                Text("Adicionar")
            }
        }

        Spacer(modifier = Modifier.height(12.dp))

        LazyColumn(modifier = Modifier.fillMaxSize()) {
            items(products) { product ->
                ProductRow(product = product, onClick = {
                    navController.navigate("${Routes.PRODUCT_DETAIL}/${product.id}")
                })
            }
        }
    }
}

@Composable
private fun ProductRow(product: Product, onClick: () -> Unit) {
    Column(modifier = Modifier
        .fillMaxWidth()
        .clickable { onClick() }
        .padding(vertical = 8.dp)) {
        Text(text = product.name ?: "Sem nome")
        Spacer(modifier = Modifier.height(4.dp))
        Text(text = "Preço: ${product.price ?: 0.0}")
    }
}
