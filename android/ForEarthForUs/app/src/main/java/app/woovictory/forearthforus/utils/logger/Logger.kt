package app.woovictory.forearthforus.utils.logger

/**
 * Created by VictoryWoo
 */
interface Logger {
    fun debug(message: String)
    fun verbose(message: String)
    fun error(message: String)
    fun information(message: String)
}