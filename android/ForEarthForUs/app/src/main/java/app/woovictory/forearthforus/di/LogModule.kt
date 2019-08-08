package app.woovictory.forearthforus.di

import app.woovictory.forearthforus.utils.NAME
import app.woovictory.forearthforus.utils.logger.LoggerImpl
import org.koin.dsl.module

/**
 * Created by VictoryWoo
 */
val logModule = module {
    single { LoggerImpl(NAME) }
}