package com.aljawad.sons.marvel.network.apiServices.interceptors


import okhttp3.Headers.Companion.toHeaders
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response
import java.io.IOException
import javax.inject.Inject
import kotlin.jvm.Throws


class HeaderRequestInterceptor @Inject constructor() : Interceptor {

    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        val original: Request = chain.request()
        val headers: HashMap<String, String> = HashMap()
        headers["Content-Type"] = "application/json"
        headers["Accept-Language"] = "en"
        val token = "8b17e33e6a67db886a0575bd0f40741cb1be13c3745889923a499be0ca1f2af7"
        headers["Authorization"] = "Bearer $token"
        val builder: Request.Builder = original
            .newBuilder()
            .headers(headers.toHeaders())
            .method(original.method, original.body)
        val newRequest: Request = builder.build()
        return chain.proceed(newRequest)
    }
}