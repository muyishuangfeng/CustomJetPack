package com.gnetop.sdk.customlivedata.bus

import androidx.lifecycle.BusLiveData
import com.gnetop.sdk.customlivedata.bus.core.LiveDataBusCore
import java.lang.reflect.InvocationHandler
import java.lang.reflect.ParameterizedType
import java.lang.reflect.Proxy


class LiveDataBus private constructor() {

    companion object {

        @Volatile
        private var instance: LiveDataBus? = null

        @JvmStatic
        fun getInstance() = instance ?: synchronized(this) {
            instance ?: LiveDataBus().also { instance = it }
        }

    }

    @Synchronized
    fun <T> getSyn(key: String): BusLiveData<T> {
        return get(key)
    }

    fun <T> get(key: String): BusLiveData<T> {
        return LiveDataBusCore.getInstance().getChannel(key)
    }


    private fun <T> get(key: String, type: Class<T>): BusLiveData<T> {
        return LiveDataBusCore.getInstance().getChannel(key)
    }

    fun <E> of(clz: Class<E>): E {
        require(clz.isInterface) { "API declarations must be interfaces." }
        require(clz.interfaces.isEmpty()) { "API interfaces must not extend other interfaces." }
        return Proxy.newProxyInstance(
            clz.classLoader,
            arrayOf(clz),
            InvocationHandler { _, method, _ ->
                return@InvocationHandler get(
                    // 事件名以集合类名_事件方法名定义
                    // 以此保证事件的唯一性
                    "${clz.canonicalName}_${method.name}",
                    (method.genericReturnType as ParameterizedType).actualTypeArguments[0].javaClass
                )
            }) as E
    }


}