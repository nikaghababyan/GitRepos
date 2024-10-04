package com.task.data.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.task.data.room.dao.ReposDao
import com.task.data.room.entities.RepoDbo

@Database(
    entities = [
        RepoDbo::class
    ],
    version = 1
)
abstract class GitReposDb : RoomDatabase() {

    abstract fun reposDao(): ReposDao
}
