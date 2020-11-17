package br.com.madebygallo.vinapp.utils

import android.os.Build
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

/**
 * Created by RaqG on 27/07/2020.
 */

fun getDateNow(): String {
    val datePattern = "dd-MM-yyyy HH:mm:ss"
    return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
        val currentDateTime = LocalDateTime.now()
        currentDateTime.format(DateTimeFormatter.ofPattern(datePattern))
    } else {
        val date = DateFormat.getDateInstance()
        val formatter = SimpleDateFormat(datePattern)
        formatter.format(date)
    }
}