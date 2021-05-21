package br.com.leogmsantos.gitrepositories.di

import androidx.room.Room
import br.com.leogmsantos.gitrepositories.R
import br.com.leogmsantos.gitrepositories.persistence.AppDatabase
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module

val repositoryModule = module {

    single {
        Room
            .databaseBuilder(
                androidApplication(),
                AppDatabase::class.java,
                androidApplication().getString(R.string.database)
            )
            .allowMainThreadQueries()
            .fallbackToDestructiveMigration()
            .build()
    }

    single { get<AppDatabase>().repositoryDao()}
}
