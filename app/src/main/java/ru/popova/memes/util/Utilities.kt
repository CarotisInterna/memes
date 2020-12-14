package ru.popova.memes.util

import android.annotation.SuppressLint
import android.widget.EditText
import java.text.SimpleDateFormat
import java.util.*

const val MEME = "meme"
const val PICTURE_URL =
    "https://sun1.informsvyaz.userapi.com/impg/dElnATEPe4eiiZ2Q3LViHAymfG815X5I" +
            "lLUnew/4RSLWDHnSRI.jpg?size=1250x1058&quality=96&proxy=1&sign=" +
            "f7f561d2fedad04430b7569d3baa6086&type=album"


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