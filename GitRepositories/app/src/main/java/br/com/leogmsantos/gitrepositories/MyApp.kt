package br.com.leogmsantos.gitrepositories

import android.app.Application
import br.com.leogmsantos.gitrepositories.di.apiModule
import br.com.leogmsantos.gitrepositories.di.repositoryModule
import br.com.leogmsantos.gitrepositories.di.retrofitModule
import br.com.leogmsantos.gitrepositories.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class MyApp : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin{
            androidLogger()
            androidContext(this@MyApp)
            modules(
                retrofitModule,
                apiModule, viewModelModule, repositoryModule
            )
        }
    }
}