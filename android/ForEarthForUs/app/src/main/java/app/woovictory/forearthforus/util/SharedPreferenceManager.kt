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
    private const val PREF_USER = "ForEarthForUs"
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


    var token: String?
        get() = preferences.getString(PREF_USER, "")
        set(value) = preferences.edit {
            it.putString(PREF_USER, value)
        }

}