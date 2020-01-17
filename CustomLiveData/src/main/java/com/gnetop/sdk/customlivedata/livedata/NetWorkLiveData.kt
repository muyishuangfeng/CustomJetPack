package com.gnetop.sdk.customlivedata.livedata

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.LiveData

class NetWorkLiveData(cxt: Context) : LiveData<NetworkInfo>() {

    private var mContext: Context? = null
    private var mFilter: IntentFilter? = null
    private var mNetWorkReceiver: NetWorkReceiver? = null

    init {
        mContext = cxt.applicationContext
        mFilter = IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION)
        mNetWorkReceiver = NetWorkReceiver()
    }

    companion object {
        private var sInstance: NetWorkLiveData? = null
        /**
         * 单例
         */
        fun getInstance(context: Context): NetWorkLiveData? {
            if (sInstance == null) {
                sInstance =
                    NetWorkLiveData(
                        context
                    )
            }
            return sInstance
        }
    }


    override fun onActive() {
        super.onActive()
        mContext?.registerReceiver(mNetWorkReceiver, mFilter)
    }

    override fun onInactive() {
        super.onInactive()
        mContext?.unregisterReceiver(mNetWorkReceiver)
    }

    /**
     * 广播
     */
    class NetWorkReceiver : BroadcastReceiver() {

        @RequiresApi(Build.VERSION_CODES.M)
        override fun onReceive(context: Context?, intent: Intent?) {
            val mManager: ConnectivityManager =
                context?.getSystemService(Context.CONNECTIVITY_SERVICE)
                        as ConnectivityManager
            val activeNetWork = mManager.activeNetworkInfo
            getInstance(context)!!.value = activeNetWork
        }

    }

}