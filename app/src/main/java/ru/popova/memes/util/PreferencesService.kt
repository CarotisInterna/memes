package ru.popova.memes.util

import android.content.Context
import ru.popova.memes.dto.AuthInfo
import ru.popova.memes.dto.UserInfo

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

    fun getUserInfo(): UserInfo {
        val sharedPreferences = ctx.getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE)
        return UserInfo(
            sharedPreferences.getInt(ID, 0),
            sharedPreferences.getString(USERNAME, "user"),
            sharedPreferences.getString(FIRSTNAME, ""),
            sharedPreferences.getString(LASTNAME, ""),
            sharedPreferences.getString(DESC, "")
        )
    }

    fun clearAuthInfo() {
        val sharedPreferences = ctx.getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE)
        val edit = sharedPreferences.edit()
        edit.remove(ID)
        edit.remove(USERNAME)
        edit.remove(FIRSTNAME)
        edit.remove(LASTNAME)
        edit.remove(DESC)
        edit.apply()
    }
}