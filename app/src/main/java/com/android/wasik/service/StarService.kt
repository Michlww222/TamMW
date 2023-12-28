package com.android.wasik.service

import com.android.wasik.data.StarResponse
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Headers

interface StarService {

    @Headers("Authorization: Bearer eyJhbGciOiJIUzI1NiIsInR5c")
    @GET("/api/starships")
    suspend fun getStarResponse(): Response<StarResponse>

    companion object {
        private const val STAR_URL = "https://swapi.dev/"

        private val logger =
            HttpLoggingInterceptor().apply {
                this.level = HttpLoggingInterceptor.Level.BODY }

        private val okHttp = OkHttpClient.Builder().apply {
            this.addInterceptor(logger) }.build()

        private val retrofit: Retrofit by lazy {
            Retrofit.Builder()
                .baseUrl(STAR_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttp)
                .build()
        }
        val starService: StarService by lazy {
            retrofit.create(StarService::class.java) }
    }
}