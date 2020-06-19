package com.example.translator.util

import android.content.Context
import androidx.preference.PreferenceManager.getDefaultSharedPreferences

object AppPrefs {

    private const val KEY_DIR_FROM = "dir_from"
    private const val KEY_DIR_TO = "dir_to"
    private const val KEY_IS_INITIAL = "initial_run"
    private const val KEY_AUTO = "key_autodetect"

    fun saveDir(context: Context, from: String, to: String) = getDefaultSharedPreferences(context)
        .edit()
        .putString(KEY_DIR_FROM, from)
        .putString(KEY_DIR_TO, to)
        .apply()

    fun saveDirFrom(context: Context, from: String) = getDefaultSharedPreferences(context)
        .edit()
        .putString(KEY_DIR_FROM, from)
        .apply()

    fun saveDirTo(context: Context, to: String) = getDefaultSharedPreferences(context)
        .edit()
        .putString(KEY_DIR_TO, to)
        .apply()

    fun setAutoDetect(context: Context, value: Boolean) = getDefaultSharedPreferences(context)
        .edit()
        .putBoolean(KEY_AUTO, value)
        .apply()

    fun isAutoDetect(context: Context) = getDefaultSharedPreferences(context)
        .getBoolean(KEY_AUTO, false)

    fun getDir(context: Context): Array<String?> {
        val preferences = getDefaultSharedPreferences(context)
        return arrayOf(
            preferences.getString(KEY_DIR_FROM, "en"),
            preferences.getString(KEY_DIR_TO, "ru")
        )
    }

    fun getDirTo(context: Context) = getDefaultSharedPreferences(context)
        .getString(KEY_DIR_TO, "ru")

    fun isInitialRun(context: Context) = getDefaultSharedPreferences(context)
        .getBoolean(KEY_IS_INITIAL, true)

    fun setIsInitial(context: Context, value: Boolean) = getDefaultSharedPreferences(context)
        .edit()
        .putBoolean(KEY_IS_INITIAL, value)
        .apply()
}
