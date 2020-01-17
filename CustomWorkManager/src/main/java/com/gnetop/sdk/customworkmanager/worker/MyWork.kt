package com.gnetop.sdk.customworkmanager.worker

import android.content.Context
import androidx.work.Worker
import androidx.work.WorkerParameters
import timber.log.Timber

class MyWork(context: Context, workerParams: WorkerParameters) : Worker(context, workerParams) {


    override fun doWork(): Result {
        return try {
            compress()
            Result.success()
        } catch (throwable: Throwable) {
            Timber.e(throwable)
            Result.failure()
        }

    }

    /**
     * 压缩
     */
    private fun compress() {
        Timber.e("开始压缩图片了...")
    }

}