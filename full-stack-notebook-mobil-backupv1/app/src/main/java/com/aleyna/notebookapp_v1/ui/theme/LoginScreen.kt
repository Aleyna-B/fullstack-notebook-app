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
fun LoginScreen(navController: NavController) {
    var username by remember {
        mutableStateOf("")
    }

    var password by remember {
        mutableStateOf("")
    }

    var responseMessage by remember {
        mutableStateOf("")
    }
    var userDetResponseMessage by remember {
        mutableStateOf("")
    }
    val scope = rememberCoroutineScope()

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
                    Text(text = "Welcome Back!", color = Color.Black, fontWeight = FontWeight.Bold, fontSize = 30.sp)
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
                                val response = RetrofitInstance.api.login(username = username,password=password)

                                responseMessage = if (response.body() == "success") {
                                    "Login successful!"
                                } else {
                                    "Login failed!"
                                }
                            } catch (e: Exception) {
                                responseMessage = "Exception: ${e.message}"
                            }
                            Log.i("Login info:",responseMessage)

                            if (responseMessage=="Login successful!"){
                                try {
                                    val userres = RetrofitInstance.api.getUserDetails()
                                    if (userres.isSuccessful) {
                                        Log.d("API", "GET request successful")
                                        //userDetResponseMessage = "User Detail obtained"
                                        val userraw = userres.raw()
                                        Log.i("User response?", userraw.toString())
                                        val userdet = userres.body()
                                        Log.i("User response body", userdet.toString())
                                        val userheaders = userres.headers()
                                        Log.i("User response headers", userheaders.toString())
                                        navController.navigate("userNotesScreen")
                                    } else {
                                        Log.e("API", "GET request failed with response code: ${userres.code()}")
                                    }
                                } catch (e: Exception) {
                                    Log.e("API", "GET request failed with exception: ${e.message}")
                                }


//                                } catch (e: Exception) {
//                                    userDetResponseMessage = "Exception: ${e.message}"
//                                }

                                Log.i("User Detail",userDetResponseMessage)
                            }
                        }}) {
                        //Log.i("Login info:",responseMessage)

                        Text(text = "Log in") //The purple color comes from the default primary color in the Material theme.
                    }
                    Spacer(modifier = Modifier.height(20.dp))
                    TextButton(onClick = { navController.navigate("signUpScreen")}) {
                        Text(text = "Sign Up for a new account!", fontWeight = FontWeight.Bold)
                    }
                }
            }
        }
    }
}