package moe.yukisora.takemymoney.networks

import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory


object RetrofitServiceGenerator {
    private val STEAMDB_BASE_URL = "https://steamdb.info/"
    private val steamdbRetrofit = Retrofit.Builder()
            .baseUrl(STEAMDB_BASE_URL)
            .addConverterFactory(ScalarsConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()

    fun steamdbGenerator(): SteamdbService = steamdbRetrofit.create(SteamdbService::class.java)
}