package br.com.leogmsantos.gitrepositories.persistence

import androidx.room.*
import br.com.leogmsantos.gitrepositories.model.GITRepository

@Dao
interface GITRepositoryDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertRepositories(repositories: List<GITRepository>)

    @Query("SELECT * FROM GITRepository ORDER BY entityId ASC")
    fun getAllRepositories(): List<GITRepository>
}