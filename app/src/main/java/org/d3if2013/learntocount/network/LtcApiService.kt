package org.d3if2013.learntocount.network

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import org.d3if2013.learntocount.model.Article
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET

private const val BASE_URL = "https://gist.githubusercontent.com/audyningrum27/5dc45204a90a6409f874af2549de5e4d/raw/120d9cdbba840fa91cee847f71a8135c115823b5/"

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL)
    .build()

interface LtcApiService {
    @GET("article.json")
    suspend fun getArticle(): List<Article>
}

object LtcApi {
    val service: LtcApiService by lazy {
        retrofit.create(LtcApiService::class.java)
    }
}

enum class ApiStatus { LOADING, SUCCESS, FAILED }