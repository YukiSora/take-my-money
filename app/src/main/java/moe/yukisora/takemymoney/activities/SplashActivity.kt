package moe.yukisora.takemymoney.activities

import android.app.Activity
import android.content.Intent
import android.os.Bundle


class SplashActivity : Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val intent = Intent("moe.yukisora.takemymoney.activities.MainActivity")
        startActivity(intent)
        finish()
    }
}
