package com.aleyna.notebookapp_v1

import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.aleyna.notebookapp_v1.ui.theme.LoginScreen
import com.aleyna.notebookapp_v1.ui.theme.NotebookApp_v1Theme
import com.aleyna.notebookapp_v1.ui.theme.SignUpScreen
import com.aleyna.notebookapp_v1.ui.theme.UserNotesScreen

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        appContext = applicationContext
        enableEdgeToEdge()
        setContent {
            val navController = rememberNavController()

            NavHost(navController = navController, startDestination = "signUpScreen") {
                composable("signUpScreen") { SignUpScreen(navController) }
                composable("loginScreen") { LoginScreen(navController) }
                composable("userNotesScreen") { UserNotesScreen() }
            }
        }

    }
    companion object {
        lateinit var appContext: Context
            private set
    }
}
