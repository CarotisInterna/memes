package ru.popova.memes.util

import android.content.Context
import ru.popova.memes.dto.AuthInfo

private const val APP_PREFERENCES = "MemesPreferences"
private const val ID = APP_PREFERENCES + "_id"
private const val USERNAME = APP_PREFERENCES + "_username"
private const val FIRSTNAME = APP_PREFERENCES + "_firstName"
private const val LASTNAME = APP_PREFERENCES + "_lastName"
private const val DESC = APP_PREFERENCES + "_description"

class PreferencesService(private val ctx: Context) {


    fun saveAuthInfo(authInfo: AuthInfo) {
        val sharedPreferences = ctx.getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE)
        val edit = sharedPreferences.edit()
        edit.putInt(ID, authInfo.userInfo.id)
        edit.putString(USERNAME, authInfo.userInfo.username)
        edit.putString(FIRSTNAME, authInfo.userInfo.firstName)
        edit.putString(LASTNAME, authInfo.userInfo.lastName)
        edit.putString(DESC, authInfo.userInfo.userDescription)
        edit.apply()
    }
}