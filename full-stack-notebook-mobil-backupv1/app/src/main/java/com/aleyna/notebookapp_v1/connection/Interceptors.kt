package com.aleyna.notebookapp_v1.connection

import okhttp3.Interceptor
import okhttp3.Response

class ReceivedCookiesInterceptor(private val cookieStore: CookieStore) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val originalResponse = chain.proceed(chain.request())
        if (originalResponse.headers("Set-Cookie").isNotEmpty()) {
            val cookies = originalResponse.headers("Set-Cookie")
            cookieStore.saveCookies(cookies)
        }
        return originalResponse
    }
}

class AddCookiesInterceptor(private val cookieStore: CookieStore) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val builder = chain.request().newBuilder()
        val cookies = cookieStore.getCookies()
        if (cookies.isNotEmpty()) {
            builder.addHeader("Cookie", cookies.joinToString("; "))
        }
        return chain.proceed(builder.build())
    }
}
