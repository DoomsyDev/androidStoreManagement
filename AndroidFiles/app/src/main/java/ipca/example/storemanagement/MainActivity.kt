package ipca.example.storemanagement

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import ipca.example.storemanagement.itui.UserSessionViewModel
import ipca.example.storemanagement.itui.navigation.AppNavigation
import ipca.example.storemanagement.ui.theme.StoreManagementTheme

class MainActivity : ComponentActivity() {

    private val userSessionViewModel: UserSessionViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            StoreManagementTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    AppNavigation(userSessionViewModel = userSessionViewModel)
                }
            }
        }
    }
}
