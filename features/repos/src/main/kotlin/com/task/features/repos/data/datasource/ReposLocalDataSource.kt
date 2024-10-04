package com.task.features.repos.data.datasource

import android.app.DownloadManager
import android.content.Context
import android.os.Environment
import androidx.core.net.toUri
import com.task.data.config.Config
import com.task.data.room.dao.ReposDao
import com.task.data.room.entities.RepoDbo
import com.task.data.utils.safeDbCall

class ReposLocalDataSource(
    private val reposDao: ReposDao,
    private val config: Config,
    context: Context
) {
    private val downloadManager = context.getSystemService(DownloadManager::class.java)

    suspend fun saveDownloadedRepo(repo: RepoDbo) = safeDbCall {
        reposDao.insertRepo(repo)
    }

    fun downloadRepo(branch: String, name: String): Long {
        val url = config.baseUrl + "repos/" + name + "/zipball" + "/"+branch
        val request = DownloadManager.Request(url.toUri())
            .setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED)
            .addRequestHeader("Authorization", "Bearer ${config.token}")
            .setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, "$name.zip")
        return downloadManager.enqueue(request)
    }

    suspend fun getDownloadedRepos() = safeDbCall {
        reposDao.getRepos()
    }
}