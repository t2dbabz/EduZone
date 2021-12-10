package com.gads.tunde.eduzone.network

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.OkHttpClient
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET

    private const val BASE_URL = "https://www.udemy.com/api-2.0/"

    private val client = OkHttpClient.Builder().apply {
        addInterceptor(MyInterceptor())
    }.build()

    private val moshi = Moshi.Builder()
        .add(KotlinJsonAdapterFactory())
        .build()

    private val retrofit = Retrofit.Builder()
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .baseUrl(BASE_URL)
        .client(client)
        .build()

    interface UdemyApiService {
    @GET("courses")
    suspend fun getCourses(): Response<UdemyApiResponse>
    }

    object UdemyApi {
        val retrofitService : UdemyApiService by lazy {
            retrofit.create(UdemyApiService::class.java)
        }
    }




