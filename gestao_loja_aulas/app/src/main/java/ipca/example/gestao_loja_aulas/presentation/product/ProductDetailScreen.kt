package ipca.example.gestao_loja_aulas.presentation.product

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import ipca.example.gestao_loja_aulas.domain.model.Product

@Composable
fun ProductDetailScreen(
    navController: NavController,
    productId: String,
    viewModel: ProductViewModel = hiltViewModel()
) {
    var productState by remember { mutableStateOf<Product?>(null) }

    LaunchedEffect(productId) {
        viewModel.getProductByIdOnce(productId) { p ->
            productState = p
        }
    }

    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        val p = productState

        if (p == null) {
            Text("A carregar...")
        } else {
            Text(text = p.name ?: "Sem nome")

            Spacer(modifier = Modifier.height(8.dp))

            Text(text = p.description ?: "")

            Spacer(modifier = Modifier.height(16.dp))

            Row {
                Button(onClick = {
                    navController.navigate("addEditProduct?productId=${p.id}")
                }) {
                    Text("Editar")
                }

                Spacer(modifier = Modifier.width(8.dp))

                Button(onClick = {
                    viewModel.deleteProduct(p.id ?: "") {
                        navController.popBackStack()
                    }
                }) {
                    Text("Eliminar")
                }
            }
        }
    }
}
