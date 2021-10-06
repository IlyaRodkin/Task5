package by.rodkin.task5.retrofit

import by.rodkin.task5.model.Cat
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query
import java.net.URL

interface RetrofitService {

    @Headers("x-api-key: a71033d8-8cc2-4741-bc94-7426921f8a3a")
    @GET("images/search")
    suspend fun loadImages(
        @Query("limit") limit: Int,
        @Query("page") page: Int
    ): List<Cat>

    companion object {
        private const val BASE_URL = "https://api.thecatapi.com/v1/"

        fun create(): RetrofitService {
            return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(RetrofitService::class.java)
        }
    }
}