package com.aleyna.notebookapp_v1.ui.theme

import android.content.Intent
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.aleyna.notebookapp_v1.R
import com.aleyna.notebookapp_v1.connection.RetrofitInstance
import com.aleyna.notebookapp_v1.models.UserInfos
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SignUpScreen(navController: NavController) {
    var username by remember {
        mutableStateOf("")
    }

    var password by remember {
        mutableStateOf("")
    }

    var responseMessage by remember {
        mutableStateOf("")
    }
    val scope = rememberCoroutineScope()

    val context = LocalContext.current

    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        Image(
            painter = painterResource(id = R.drawable.bgimage),
            contentDescription = "sign up screen image",
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop // Ensures the image covers the screen
        )
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
        ){
            ElevatedCard(
                elevation = CardDefaults.cardElevation(
                    defaultElevation = 10.dp
                ),
                colors = CardDefaults.elevatedCardColors(
                    containerColor = Color.White.copy(alpha = 0.3f)
                ),
                modifier = Modifier
                    .size(width = 270.dp, height = 500.dp)
                    .align(alignment = Alignment.Center),
            ) {
                Column(
                    modifier = Modifier.padding(16.dp),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Spacer(modifier = Modifier.height(100.dp))
                    Text(text = "Welcome!", color = Color.Black, fontWeight = FontWeight.Bold, fontSize = 30.sp)
                    Spacer(modifier = Modifier.height(50.dp))
                    TextField(
                        value = username,
                        onValueChange = {username = it},
                        label = {Text ("Username") },
                        shape = RoundedCornerShape(8.dp),
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    TextField(
                        value = password,
                        onValueChange = {password = it},
                        label = { Text("Password") },
                        visualTransformation = PasswordVisualTransformation() //görünmesin diye
                    )
                    Spacer(modifier = Modifier.height(22.dp))
                    Button(onClick = { Log.i("Credential","Email: $username , Password: $password")
                        scope.launch {
                            try {
                                val userInfos = UserInfos(username, password)
                                val response = RetrofitInstance.api.signUp(userInfos)

                                    responseMessage = if (response.body() == true) {
                                        "Sign up successful!"
                                    } else {
                                        "Sign up failed!"
                                    }


                            } catch (e: Exception) {
                                responseMessage = "Exception: ${e.message}"
                            }
                        }}) {
                        Text(text = "Sign Up") //The purple color comes from the default primary color in the Material theme.
                    }
                    Spacer(modifier = Modifier.height(20.dp))
                    TextButton(onClick = { navController.navigate("loginScreen") }) {
                        Text(text = "Login if you have an account", fontWeight = FontWeight.Bold)
                    }
                }
            }
        }
    }
}