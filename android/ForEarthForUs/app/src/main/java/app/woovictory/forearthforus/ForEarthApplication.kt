package app.woovictory.forearthforus

import android.app.Application
import app.woovictory.forearthforus.di.appModules
import app.woovictory.forearthforus.data.SharedPreferenceManager
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

/**
 * Created by VictoryWoo
 */
class ForEarthApplication : Application() {


    override fun onCreate() {
        super.onCreate()
        SharedPreferenceManager.init(applicationContext)
        startKoin {
            androidContext(this@ForEarthApplication)
            modules(appModules)
        }

    }
}