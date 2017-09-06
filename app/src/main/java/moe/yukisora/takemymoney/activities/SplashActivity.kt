package moe.yukisora.takemymoney.activities

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import com.jakewharton.picasso.OkHttp3Downloader
import com.squareup.picasso.Picasso
import moe.yukisora.takemymoney.TakeMyMoneyApplication
import okhttp3.Cache
import okhttp3.CacheControl
import okhttp3.OkHttpClient
import java.util.concurrent.TimeUnit


class SplashActivity : Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // okhttp
        TakeMyMoneyApplication.okHttpClient = OkHttpClient.Builder()
                .retryOnConnectionFailure(true)
                .connectTimeout(20, TimeUnit.SECONDS)
                .writeTimeout(20, TimeUnit.SECONDS)
                .readTimeout(20, TimeUnit.SECONDS)
                .cache(Cache(cacheDir, 5 * 1024 * 1024))
                .addNetworkInterceptor { chain ->
                    val cacheControl = CacheControl.Builder()
                            .maxAge(5, TimeUnit.MINUTES)
                            .build()
                            .toString()

                    chain.proceed(chain.request()).newBuilder()
                            .header("Cache-Control", cacheControl)
                            .header("User-Agent", " Mozilla/5.0 (X11; Linux x86_64; rv:55.0) Gecko/20100101 Firefox/55.0")
                            .build()
                }
                .build()

        // picasso
        Picasso.setSingletonInstance(Picasso.Builder(this)
                .downloader(OkHttp3Downloader(TakeMyMoneyApplication.okHttpClient))
                .build()
        )

        val intent = Intent("moe.yukisora.takemymoney.activities.MainActivity")
        startActivity(intent)
        finish()
    }
}
