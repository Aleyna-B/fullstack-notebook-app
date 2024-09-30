package com.aleyna.notebookapp_v1.connection

import android.content.Context

class CookieStore(context: Context) {
    private val sharedPreferences = context.getSharedPreferences("cookie_prefs", Context.MODE_PRIVATE)

    fun saveCookies(cookies: List<String>) {
        with(sharedPreferences.edit()) {
            putStringSet("cookies", cookies.toSet())
            apply()
        }
    }

    fun getCookies(): List<String> {
        return sharedPreferences.getStringSet("cookies", emptySet())?.toList() ?: emptyList()
    }
}
