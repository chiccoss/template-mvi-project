package fr.sohayb.marvelapp

import android.app.Application

import fr.sohayb.marvelapp.app.injection.AppModule
import fr.sohayb.marvelapp.app.injection.DataSourceModule
import fr.sohayb.marvelapp.app.database.DataBaseFactory.Companion.initialize
import fr.sohayb.marvelapp.app.injection.NetworkModule
import fr.sohayb.marvelapp.main.injection.MainModule
import org.koin.android.BuildConfig
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level
import timber.log.Timber

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        initialize(applicationContext)
        if(BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }

        startKoin {
            if(BuildConfig.DEBUG) {
                androidLogger(level = Level.DEBUG)
            }
            androidContext(this@App)
            modules(
                AppModule,
                MainModule,
                NetworkModule,
                DataSourceModule
            )
        }
    }
}