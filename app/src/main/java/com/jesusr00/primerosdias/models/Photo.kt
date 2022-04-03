package com.jesusr00.primerosdias.models

import android.graphics.Bitmap
import android.graphics.BitmapFactory

class Photo(val id: Int?,val photo: ByteArray?) {

    public fun getPhoto(): Bitmap {
        return BitmapFactory.decodeByteArray(photo, 0, photo!!.size)
    }
}