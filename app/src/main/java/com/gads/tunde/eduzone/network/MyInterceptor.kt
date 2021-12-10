package com.gads.tunde.eduzone.network

import android.util.Base64
import okhttp3.Interceptor
import okhttp3.Response

class MyInterceptor: Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val CLIENT_ID = "aKfx5HnFnQRiuBETu3NR6BoFql7RJeImtDvxfXFX"
        val CLIENT_SECRET = "Clg9QARs4Li2YLQD6KyhRpPsLpCSuWnFLOifZi2zbYM41rKU3qZ89IWBKy3NjzLm7UJaOeVt9AUSuylm4VdymwjyzTBdh0hy8UEankB7aGj0JURxgEiBwTLearaIkqoQ"

        val authPayload = "$CLIENT_ID:$CLIENT_SECRET"
        val data = authPayload.toByteArray()
        val base64 = Base64.encodeToString(data, Base64.NO_WRAP)
        val request = chain.request()
            .newBuilder()
            .addHeader("Content-Type", "application/json")
            .addHeader("Authorization", "Basic $base64".trim())
            .build()
        return chain.proceed(request)
    }
}