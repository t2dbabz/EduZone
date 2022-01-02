package com.gads.tunde.eduzone.network

import android.util.Base64
import com.gads.tunde.eduzone.BuildConfig
import okhttp3.Interceptor
import okhttp3.Response

class MyInterceptor: Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val CLIENT_ID = BuildConfig.CLIENT_ID
        val CLIENT_SECRET = BuildConfig.CLIENT_SECRET

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