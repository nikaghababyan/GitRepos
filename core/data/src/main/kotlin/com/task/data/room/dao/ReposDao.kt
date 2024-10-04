package com.task.data.room.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.task.data.room.entities.RepoDbo

@Dao
interface ReposDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertRepo(repo: RepoDbo)

    @Delete
    suspend fun deleteRepo(repo: RepoDbo)

    @Query("SELECT * FROM repos_table")
    suspend fun getRepos(): List<RepoDbo>
}
