package com.gads.tunde.eduzone.network

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import kotlinx.coroutines.Deferred
import okhttp3.OkHttpClient
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

private const val BASE_URL = "https://www.udemy.com/api-2.0/"

    private val client = OkHttpClient.Builder().apply {
        addInterceptor(MyInterceptor())
    }.build()

    private val moshi = Moshi.Builder()
        .add(KotlinJsonAdapterFactory())
        .build()

    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .client(client)
        .build()

    interface UdemyApiService {
        @GET("courses")
        suspend fun getCourses(): UdemyApiResponse

        @GET("courses")
        suspend fun searchCourses(
            @Query("search") searchTerm: String,
            @Query("ordering") order: String = "highest-rated",
            @Query("page_size") pageSize : Int = 20,
        ): UdemyApiResponse

        @GET("courses")
        suspend fun getFeaturedCourses(
            @Query("ordering") order: String = "relevance",
            @Query("ratings") rating : Double = 4.5
        ): UdemyApiResponse

        @GET("courses")
        suspend fun getTopCourses(
            @Query("page_size") pageSize: Int = 20,
            @Query("price") pricePaid: String = "price-paid",
            @Query("instructional_level") level: String = "intermediate",
            @Query("ordering") order: String = "highest-rated",
            @Query("ratings") rating : Double = 4.5
        ): UdemyApiResponse

        @GET("courses")
        suspend fun getNewCourses(
            @Query("page_size") pageSize: Int = 20,
            @Query("price") pricePaid: String = "price-paid",
            @Query("instructional_level") level: String = "all",
            @Query("ordering") order: String = "newest"
        ) : UdemyApiResponse
    }

    object UdemyApi {
        val retrofitService : UdemyApiService by lazy {
            retrofit.create(UdemyApiService::class.java)
        }
    }




