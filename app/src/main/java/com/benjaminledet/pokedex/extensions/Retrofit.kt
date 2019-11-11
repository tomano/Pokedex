package com.benjaminledet.pokedex.extensions

import okhttp3.Authenticator
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

inline fun <reified T> Retrofit.Builder.createDefaultService(
    baseUrl: String,
    interceptor: Interceptor? = null,
    authenticator: Authenticator? = null): T = createDefaultService(T::class.java, baseUrl, interceptor, authenticator)

fun <T> Retrofit.Builder.createDefaultService(
    serviceClass: Class<T>,
    baseUrl: String,
    interceptor: Interceptor? = null,
    authenticator: Authenticator? = null): T {

    val okHttp = OkHttpClient.Builder()
        .readTimeout(20, TimeUnit.SECONDS)
        .connectTimeout(20, TimeUnit.SECONDS)
       // .addInterceptor(HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BODY })

    if (interceptor != null) okHttp.addInterceptor(interceptor)

    if (authenticator != null) okHttp.authenticator(authenticator)

    addConverterFactory(GsonConverterFactory.create())
    client(okHttp.build())
    baseUrl(baseUrl)

    return build().create(serviceClass)
}