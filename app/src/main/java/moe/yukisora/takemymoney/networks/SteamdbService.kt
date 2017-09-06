package moe.yukisora.takemymoney.networks

import io.reactivex.Observable
import retrofit2.http.GET


interface SteamdbService {
    @GET("sales/")
    fun getSpecials(): Observable<String>
}