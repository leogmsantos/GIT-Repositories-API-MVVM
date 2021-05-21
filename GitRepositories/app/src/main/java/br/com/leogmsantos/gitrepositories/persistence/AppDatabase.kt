package br.com.leogmsantos.gitrepositories.persistence

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import br.com.leogmsantos.gitrepositories.model.GITRepository


@Database(entities = [GITRepository::class], version = 1, exportSchema = true)
@TypeConverters(value = [Converter::class])
abstract class AppDatabase : RoomDatabase() {
    abstract fun repositoryDao() : GITRepositoryDao
}