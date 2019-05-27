package app.woovictory.forearthforus.util

import android.content.Context
import android.content.SharedPreferences
import java.text.SimpleDateFormat
import java.util.*

/**
 * Created by VictoryWoo
 */
class SharedPreferenceServcie private constructor() {

    var preferencePassword: String?
        get() = getPreferencePassword("")
        set(password) {
            val editor = preferences!!.edit()
            if (password == null) {
                editor.remove(PREF_PASSWORD)
                editor.apply()
            } else {
                editor.putString(PREF_PASSWORD, password)
                editor.apply()
            }
        }

    var preferencePushTime: String?
        get() = getPreferencePushTime(null)
        set(pushTime) {
            val editor = preferences!!.edit()
            editor.putString(PREF_PUSH_TIME, pushTime)
            editor.apply()
        }

    val lastDiarySaveTime: String
        get() = preferences!!.getString(PREF_LAST_SAVE_TIME, "")

    var workerState: Boolean
        get() = preferences!!.getBoolean(PREF_WORKER, false)
        set(workerState) {
            val editor = preferences!!.edit()
            editor.putBoolean(PREF_WORKER, workerState)
            editor.apply()
        }

    val isPasswordSaved: Boolean
        get() = preferences!!.contains(PREF_PASSWORD)

    fun getPreferencePassword(defaultPassword: String): String? {
        return preferences!!.getString(PREF_PASSWORD, defaultPassword)
    }

    fun checkPassword(inputPassword: String?): Boolean {
        return inputPassword == preferences!!.getString(PREF_PASSWORD, "")
    }

    fun getPreferencePushTime(defaultPushTime: String?): String? {
        return preferences!!.getString(PREF_PUSH_TIME, defaultPushTime)
    }

    fun removePreferencePushTime() {
        val editor = preferences!!.edit()
        editor.remove(PREF_PUSH_TIME)
        editor.apply()
    }

    fun removeAllData() {
        val editor = preferences!!.edit()
        editor.clear()
        editor.apply()
    }

    fun setLastDiarySaveTime(lastSaveTime: Date) {
        val editor = preferences!!.edit()
        editor.putString(
            PREF_LAST_SAVE_TIME,
            SimpleDateFormat("yyyy-MM-dd", Locale.KOREA).format(lastSaveTime)
        )
        editor.apply()
    }

    fun removeLastDiarySaveTime() {
        val editor = preferences!!.edit()
        editor.remove(PREF_LAST_SAVE_TIME)
        editor.apply()
    }

    // LazyHolder 클래스 - 싱글톤
    private object LazyHolder {
        val INSTANCE = SharedPreferenceServcie()
    }

    fun getPreferences(): SharedPreferences? {
        return preferences
    }

    companion object {
        private const val TEAM_H = "TEAM_H"
        private const val PREF_PASSWORD = "PREF_PASSWORD"
        private const val PREF_PUSH_TIME = "PREF_PUSH_TIME"
        private const val PREF_LAST_SAVE_TIME = "PREF_LAST_SAVE_TIME"
        private const val PREF_WORKER = "PREF_WORKER"

        // fields
        private var preferences: SharedPreferences? = null

        // getInstance()
        fun getInstance(context: Context): SharedPreferenceServcie {
            if (preferences == null) {
                preferences = context.getSharedPreferences(TEAM_H, Context.MODE_PRIVATE)
            }
            return LazyHolder.INSTANCE
        }
    }
}