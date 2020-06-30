package co.lap.todolap

import android.app.Application
import co.lap.todolap.koin.databaseModule
import co.lap.todolap.koin.repositoryModule
import co.lap.todolap.koin.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class MainApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin{
            androidLogger()
            androidContext(this@MainApplication)
            modules(listOf(databaseModule, viewModelModule, repositoryModule))
        }
    }
}