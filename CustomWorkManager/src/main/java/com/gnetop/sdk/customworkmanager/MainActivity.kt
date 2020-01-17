package com.gnetop.sdk.customworkmanager

import android.Manifest
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.work.*
import com.gnetop.sdk.customworkmanager.worker.MyWork
import com.yk.silent.permission.HiPermission
import com.yk.silent.permission.impl.PermissionCallback
import com.yk.silent.permission.model.PermissionItem
import kotlinx.android.synthetic.main.activity_main.*
import timber.log.Timber
import timber.log.Timber.DebugTree
import java.util.concurrent.TimeUnit


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initView()
    }


    private fun initView() {
        if (BuildConfig.DEBUG) {
            Timber.plant(DebugTree())
        } else {
            Timber.plant(CrashReportingTree())
        }


        val permissionList = ArrayList<PermissionItem>()
        permissionList.add(
            PermissionItem(
                Manifest.permission.READ_EXTERNAL_STORAGE,
                resources.getString(R.string.text_read), R.drawable.permission_ic_storage
            )
        )
        permissionList.add(
            PermissionItem(
                Manifest.permission.WRITE_EXTERNAL_STORAGE,
                resources.getString(R.string.text_write), R.drawable.permission_ic_storage
            )
        )

        HiPermission.create(this)
            .permissions(permissionList)
            .checkMutiPermission(object : PermissionCallback {
                override fun onFinish() {

                }

                override fun onDeny(permission: String?, position: Int) {
                    Timber.e("拒绝=======$permission")
                }

                override fun onGuarantee(permission: String?, position: Int) {
                    Timber.e("同意=======$permission")
                }

                override fun onClose() {

                }

            })

        btn_select.setOnClickListener {

            val constraints = Constraints.Builder()
                .setRequiredNetworkType(NetworkType.CONNECTED)//有网络连接时才执行任务
                //.setRequiresDeviceIdle(true) //设备休眠时执行任务
                .setRequiresCharging(true)//设备充电时执行任务
                //还有其它的约束，可根据具体业务设置
                .build()


            val request = OneTimeWorkRequest.Builder(MyWork::class.java)
                .addTag("A")//标记
                .setConstraints(constraints)//约束条件
                .build()
            val mRequest=PeriodicWorkRequest.Builder(MyWork::class.java,
                15,TimeUnit.SECONDS)
                .build()

            WorkManager.getInstance(this).enqueue(request)
//            WorkManager.getInstance(this)
//                .getWorkInfoByIdLiveData(request.id)
//                .observe(this, Observer {
//                    when (it.state) {
//                        WorkInfo.State.BLOCKED -> { //任务准备阶段
//                            Timber.e("任务准备阶段")
//                        }
//                        WorkInfo.State.ENQUEUED -> {//任务被添加，准备执行
//                            Timber.e("任务被添加，准备执行")
//                        }
//                        WorkInfo.State.RUNNING -> {//任务正在执行
//                            Timber.e("任务正在执行")
//                        }
//                        WorkInfo.State.SUCCEEDED -> {//任务执行成功
//                            Timber.e("任务执行成功")
//                        }
//                        WorkInfo.State.CANCELLED -> {//任务被取消
//                            Timber.e("任务被取消")
//                        }
//                        WorkInfo.State.FAILED -> {//任务出错
//                            Timber.e("任务出错")
//                        }
//                    }
//                })

        }

        btn_cancel.setOnClickListener {

        }
    }

    private class CrashReportingTree : Timber.Tree() {
        override fun log(
            priority: Int,
            tag: String?,
            message: String,
            t: Throwable?
        ) {
        }
    }


}
