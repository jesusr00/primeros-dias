package com.jesusr00.primerosdias

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowManager
import kotlinx.coroutines.*

class SplashActivity : AppCompatActivity() {
    @OptIn(DelicateCoroutinesApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )

        GlobalScope.launch(Dispatchers.Main) {
            val intent = Intent(this@SplashActivity, MainActivity::class.java)
            //delay(3000)
            delay(30)
            startActivity(intent)
        }
    }
}