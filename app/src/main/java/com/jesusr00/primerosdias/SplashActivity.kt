package com.jesusr00.primerosdias

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.WindowManager
import com.jesusr00.primerosdias.utils.CustomAssetsManager
import kotlinx.coroutines.*
import java.io.File

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
            val customAssetsManager = CustomAssetsManager(this@SplashActivity)
            val images = customAssetsManager.getImages()
            val map = customAssetsManager.getMap()
            if (images.isEmpty()) {
                Log.d("SplashActivity", "No hay imagenes")
                Log.d("SplashActivity", "Copiando imagenes")
                customAssetsManager.copyAssetsImages()
            }
            if (!map.exists()){
                Log.d("SplashActivity", "No hay mapa")
                Log.d("SplashActivity", "Copiando mapa")
                customAssetsManager.copyAssetsMap()
            }

            val intent = Intent(this@SplashActivity, MainActivity::class.java)
            //delay(3000)
            delay(30)
            startActivity(intent)
        }
    }
}