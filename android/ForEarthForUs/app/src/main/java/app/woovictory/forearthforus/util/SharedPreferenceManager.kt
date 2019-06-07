package app.woovictory.forearthforus.util

import android.content.Context
import android.content.SharedPreferences

/**
 * Created by VictoryWoo
 * 확장 함수를 사용해서 SharedPreference 구현.
 * 사용 시 token 과 같은 변수 추가해서 사용하면 된다.
 */
object SharedPreferenceManager {

    private const val NAME = "ForEarth"
    private const val PREF_USER_TOKEN = "ForEarthForUs"
    private const val PREF_EARTH_LEVEL = "EarthLevel"
    private const val PREF_USER_NAME = "UserName"
    private const val PREF_USER_ID = "UserId"
    private const val MODE = Context.MODE_PRIVATE
    private lateinit var preferences: SharedPreferences

    fun init(context: Context) {
        preferences = context.getSharedPreferences(NAME, MODE)
    }


    private inline fun SharedPreferences.edit(operation: (SharedPreferences.Editor) -> Unit) {
        val editor = edit()
        operation(editor)
        editor.apply()
    }

    fun removeAllData() {
        val editor = preferences.edit()
        editor.clear()
        editor.apply()
    }

    var token: String
        get() = preferences.getString(PREF_USER_TOKEN, " ")!!
        set(value) = preferences.edit {
            it.putString(PREF_USER_TOKEN, value)
        }

    var earthLevel: Int
        get() = preferences.getInt(PREF_EARTH_LEVEL, 1)
        set(value) = preferences.edit {
            it.putInt(PREF_EARTH_LEVEL, value)
        }

    var userName: String
        get() = preferences.getString(PREF_USER_NAME, " ")!!
        set(value) = preferences.edit {
            it.putString(PREF_USER_NAME, value)
        }

    var userId: Int
        get() = preferences.getInt(PREF_USER_ID, 0)
        set(value) = preferences.edit {
            it.putInt(PREF_USER_ID, value)
        }
}