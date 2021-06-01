package dev.feryadi.bayu.interceptor

import dev.feryadi.bayu.model.UnauthorizedEvent
import okhttp3.Interceptor
import okhttp3.Response
import org.greenrobot.eventbus.EventBus

class UnauthorizedInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val response = chain.proceed(chain.request())
        if (response.code() == 401) {
            EventBus.getDefault().post(UnauthorizedEvent.instance)
        }
        return response
    }
}