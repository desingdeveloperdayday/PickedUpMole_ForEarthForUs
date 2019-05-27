package app.woovictory.forearthforus

import android.app.Application
import app.woovictory.forearthforus.di.appModules
import app.woovictory.forearthforus.util.SharedPreferenceManager
import org.koin.android.ext.android.startKoin

/**
 * Created by VictoryWoo
 */
class ForEarthApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        SharedPreferenceManager.init(applicationContext)
        startKoin(applicationContext, appModules)

    }
}