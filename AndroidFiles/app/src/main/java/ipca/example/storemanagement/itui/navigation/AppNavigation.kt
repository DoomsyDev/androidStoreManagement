package ipca.example.storemanagement.itui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import ipca.example.storemanagement.itui.home.HomeScreen
import ipca.example.storemanagement.itui.login.LoginScreen

object AppRoutes {
    const val LOGIN_SCREEN = "login"
    const val HOME_SCREEN = "home"
}

@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = AppRoutes.LOGIN_SCREEN
    ) {
        composable(AppRoutes.LOGIN_SCREEN) {
            LoginScreen(
                onLoginSuccess = {
                    navController.navigate(AppRoutes.HOME_SCREEN) {
                        popUpTo(AppRoutes.LOGIN_SCREEN) { inclusive = true }
                    }
                },
                onNavigateToRegister = {
                }
            )
        }

        composable(AppRoutes.HOME_SCREEN) {
            HomeScreen()
        }
    }
}
