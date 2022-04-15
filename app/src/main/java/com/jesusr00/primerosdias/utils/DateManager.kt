package com.jesusr00.primerosdias.utils

import java.text.SimpleDateFormat
import java.util.*

class DateManager {
     fun getDateTime(s: Long): String? {
        return try {
            val sdf = SimpleDateFormat("hh:mm a", Locale.getDefault())
            val netDate = Date(s*1000)
            sdf.format(netDate)
        } catch (e: Exception) {
            e.toString()
        }
    }
}