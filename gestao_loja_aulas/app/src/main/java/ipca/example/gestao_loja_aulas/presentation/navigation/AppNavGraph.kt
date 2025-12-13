package ipca.example.gestao_loja_aulas.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import ipca.example.gestao_loja_aulas.presentation.product.AddEditProductScreen
import ipca.example.gestao_loja_aulas.presentation.product.ProductDetailScreen
import ipca.example.gestao_loja_aulas.presentation.product.ProductListScreen
import ipca.example.gestao_loja_aulas.presentation.user.LoginScreen
import ipca.example.gestao_loja_aulas.presentation.user.RegisterScreen

object Routes {
    const val LOGIN = "login"
    const val REGISTER = "register"
    const val PRODUCTS = "products"
    const val PRODUCT_DETAIL = "product"
    const val ADD_EDIT_PRODUCT = "addEditProduct"
}

@Composable
fun AppNavGraph() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Routes.LOGIN
    ) {
        composable(Routes.LOGIN) {
            LoginScreen(navController)
        }

        composable(Routes.REGISTER) {
            RegisterScreen(navController)
        }

        composable(Routes.PRODUCTS) {
            ProductListScreen(navController)
        }

        // Product detail expects an argument productId
        composable(
            route = "${Routes.PRODUCT_DETAIL}/{productId}",
            arguments = listOf(navArgument("productId") { type = NavType.StringType })
        ) { backStackEntry ->
            val productId = backStackEntry.arguments?.getString("productId") ?: ""
            ProductDetailScreen(navController, productId)
        }

        // Add/Edit product: optional productId query param
        composable(
            route = "${Routes.ADD_EDIT_PRODUCT}?productId={productId}",
            arguments = listOf(
                navArgument("productId") {
                    type = NavType.StringType
                    defaultValue = ""
                    nullable = true
                }
            )
        ) { backStackEntry ->
            val productId = backStackEntry.arguments?.getString("productId") ?: ""
            AddEditProductScreen(navController, productId)
        }
    }
}
