package ru.popova.memes.util

import android.annotation.SuppressLint
import android.widget.EditText
import java.text.SimpleDateFormat
import java.util.*

fun EditText.isValid(): Boolean {
    return this.editableText?.isNotBlank() ?: false
}

@SuppressLint("SimpleDateFormat")
fun toDate(millis: Long): String {
    val simpleDateFormat = SimpleDateFormat("dd/MM/yyyy hh:mm")
    val cal = Calendar.getInstance()
    cal.timeInMillis = millis
    return simpleDateFormat.format(cal.time)
}