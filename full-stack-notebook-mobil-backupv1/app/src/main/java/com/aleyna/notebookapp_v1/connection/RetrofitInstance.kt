package com.aleyna.notebookapp_v1.connection

import com.aleyna.notebookapp_v1.MainActivity
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

private val cookieStore = CookieStore(MainActivity.appContext)

private val client = OkHttpClient.Builder()
    .addInterceptor(AddCookiesInterceptor(cookieStore))
    .addInterceptor(ReceivedCookiesInterceptor(cookieStore))
    .build()

object RetrofitInstance {
    val gson = GsonBuilder()
        .setLenient()
        .create()

    val api: INotebookApi by lazy {
        Retrofit.Builder()
            .baseUrl("http://10.0.2.2:8080/v1/notebook/")
            .addConverterFactory(GsonConverterFactory.create(gson))
            .client(client)
            .build()
            .create(INotebookApi::class.java)
    }
}