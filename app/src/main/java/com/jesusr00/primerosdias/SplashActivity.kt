package com.jesusr00.primerosdias

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.jesusr00.primerosdias.utils.CustomAssetsManager
import kotlinx.coroutines.*

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.SplashTheme)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        MainScope().launch(Dispatchers.Main) {
            val customAssetsManager = CustomAssetsManager(this@SplashActivity)
            val images = customAssetsManager.getImages()
            val map = customAssetsManager.getMap()
            val videos = customAssetsManager.getVideos()
            val json = customAssetsManager.getJson()
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
            if (videos.isEmpty()){
                Log.d("SplashActivity", "No hay videos")
                Log.d("SplashActivity", "Copiando videos")
                customAssetsManager.copyAssetsVideos()
            }
            if (json.isEmpty()){
                Log.d("SplashActivity", "No hay json")
                Log.d("SplashActivity", "Copiando json")
                customAssetsManager.copyAssetsJson()
            }

            val intent = Intent(this@SplashActivity, MainActivity::class.java)
            delay(300)
            startActivity(intent)
        }
    }
}