package ipca.example.storemanagement.itui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import ipca.example.storemanagement.itui.UserSessionViewModel
import ipca.example.storemanagement.itui.home.HomeScreen
import ipca.example.storemanagement.itui.login.LoginScreen
import ipca.example.storemanagement.itui.profile.ProfileScreen
import ipca.example.storemanagement.itui.register.RegisterScreen

object AppRoutes {
    const val LOGIN_SCREEN = "login"
    const val REGISTER_SCREEN = "register"
    const val HOME_SCREEN = "home"
    const val PROFILE_SCREEN = "profile"
}

@Composable
fun AppNavigation(userSessionViewModel: UserSessionViewModel) {
    val navController = rememberNavController()
    val currentUser by userSessionViewModel.currentUser.collectAsState()

    //define o ecra inicial baseado se há ulilizador logado
    val startDestination = if (currentUser != null) {
        AppRoutes.HOME_SCREEN
    } else {
        AppRoutes.LOGIN_SCREEN
    }

    NavHost(
        navController = navController,
        startDestination = startDestination
    ) {
        composable(AppRoutes.LOGIN_SCREEN) {
            LoginScreen(
                userSessionViewModel = userSessionViewModel,
                onLoginSuccess = {
                    navController.navigate(AppRoutes.HOME_SCREEN) {
                        popUpTo(AppRoutes.LOGIN_SCREEN) { inclusive = true }
                    }
                },
                onNavigateToRegister = {
                    navController.navigate(AppRoutes.REGISTER_SCREEN)
                }
            )
        }

        composable(AppRoutes.REGISTER_SCREEN) {
            RegisterScreen(
                userSessionViewModel = userSessionViewModel,
                onRegisterSuccess = {
                    navController.navigate(AppRoutes.HOME_SCREEN) {
                        popUpTo(navController.graph.startDestinationId) {
                            inclusive = true
                        }
                    }
                    navController.graph.setStartDestination(AppRoutes.HOME_SCREEN)
                },
                onNavigateBack = {
                    navController.navigateUp()
                }
            )
        }

        composable(AppRoutes.HOME_SCREEN) {
            HomeScreen(
                onNavigateToProfile = {
                    navController.navigate(AppRoutes.PROFILE_SCREEN)
                }
            )
        }

        composable(AppRoutes.PROFILE_SCREEN) {
            ProfileScreen(
                userSessionViewModel = userSessionViewModel,
                onNavigateBack = {
                    navController.navigateUp()
                },
                onLogout = {
                    navController.navigate(AppRoutes.LOGIN_SCREEN) {
                        popUpTo(0) { inclusive = true }
                    }
                }
            )
        }
    }
}