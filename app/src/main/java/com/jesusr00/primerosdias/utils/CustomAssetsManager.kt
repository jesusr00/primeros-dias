package com.jesusr00.primerosdias.utils

import android.content.Context
import android.content.res.AssetManager
import android.util.Log
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.json.JSONObject
import java.io.*


class CustomAssetsManager(val context: Context) {

    fun copyAssetsMap() = copyAssets("maps")
    fun copyAssetsImages() = copyAssets("images")
    fun copyAssetsVideos() = copyAssets("videos")
    fun copyAssetsJson() = copyAssets("json")

    fun getVideos(): ArrayList<File> {
        val videos = ArrayList<File>()
        val assets = context.getExternalFilesDir("videos")
        val files = assets?.list()
        files?.forEach {
            videos.add(File(assets, it))
        }
        return videos
    }

    fun getImages(): ArrayList<File> {
        val images = ArrayList<File>()
        val assets = context.getExternalFilesDir("images")
        val files = assets?.list()
        for (file in files!!) {
            images.add(File(assets, file))
        }
        return images
    }

    fun getMap(): File {
        return File(context.getExternalFilesDir("maps"), "uci.map")
    }

//    fun getGCEInfo(): String{
//        val file = File(context.getExternalFilesDir("json"), "gce.json")
//        val inputStream = FileInputStream(file)
//        val reader = BufferedReader(InputStreamReader(inputStream))
//        val stringBuilder = StringBuilder()
//        var line: String? = reader.readLine()
//        while (line != null) {
//            stringBuilder.append(line)
//            line = reader.readLine()
//        }
//        reader.close()
//        return stringBuilder.toString()
//    }

    fun getCGEInfo(): JSONObject {
        val json = this.getJson("gce-info.json").bufferedReader().use {
            it.readText()
        }
        return JSONObject(json)
    }

    private fun getJson(fileName: String): File {
        return File(context.getExternalFilesDir("json"), fileName)
    }

    fun getJson(): ArrayList<File> {
        val json = ArrayList<File>()
        val assets = context.getExternalFilesDir("json")
        val files = assets?.list()
        for (file in files!!) {
            json.add(File(assets, file))
        }
        return json
    }

    private fun copyAssets(path: String = "") {
        val assetManager: AssetManager = context.assets
        var files: Array<String>? = null
        try {
            files = assetManager.list(path)
        } catch (e: IOException) {
            Log.e("tag", "Failed to get asset file list.", e)
        }
        if (files != null) for (filename in files) {
            Log.d("File", filename)
            var `in`: InputStream? = null
            var out: OutputStream? = null
            try {
                `in` = assetManager.open("$path/$filename")
                val outFile = File(context.getExternalFilesDir(null), "$path/$filename")
                out = FileOutputStream(outFile)
                copyFile(`in`, out)
            } catch (e: IOException) {
                Log.e("tag", "Failed to copy asset file: \"$path/$filename\"", e)
            } finally {
                if (`in` != null) {
                    try {
                        `in`.close()
                    } catch (e: IOException) {
                    }
                }
                if (out != null) {
                    try {
                        out.flush()
                        out.close()
                    } catch (e: IOException) {
                    }
                }
            }
        }
    }

    @Throws(IOException::class)
    fun copyFile(`in`: InputStream, out: OutputStream) {
        val buffer = ByteArray(1024)
        var read: Int
        while (`in`.read(buffer).also { read = it } != -1) {
            out.write(buffer, 0, read)
        }
    }
}