package app.woovictory.forearthforus.utils.logger

import android.util.Log

/**
 * Created by VictoryWoo
 */
class LoggerImpl(private val name: String) : Logger {
    override fun debug(message: String) {
        Log.d(name, message)
    }

    override fun verbose(message: String) {
        Log.v(name, message)
    }

    override fun error(message: String) {
        Log.e(name, message)
    }

    override fun information(message: String) {
        Log.i(name, message)
    }
}