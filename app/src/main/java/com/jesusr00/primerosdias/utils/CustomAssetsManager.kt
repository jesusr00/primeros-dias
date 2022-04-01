package com.jesusr00.primerosdias.utils

import android.content.Context
import android.content.res.AssetManager
import android.util.Log
import java.io.*


class CustomAssetsManager(val context: Context) {

    fun copyAssetsMap() = copyAssets("maps")
    fun copyAssetsImages() = copyAssets("images")

    fun getImages(): ArrayList<File> {
        val images = ArrayList<File>()
        val assetManager = context.assets
        val files = assetManager.list("images")
        for (file in files!!) {
            images.add(File(context.getExternalFilesDir("images"), file))
        }
        return images
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