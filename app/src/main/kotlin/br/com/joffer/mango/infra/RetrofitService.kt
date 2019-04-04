package br.com.joffer.mango.infra

import android.content.Context

import java.io.File
import java.util.concurrent.TimeUnit

import br.com.joffer.mango.BuildConfig
import okhttp3.Cache
import okhttp3.CacheControl
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

open class RetrofitService<T>(clss: Class<T>, baseUrl: String) {

    var apiService: T
    var authOkHttpClient: OkHttpClient
    var retrofit: Retrofit

    init {
        val logging = HttpLoggingInterceptor()
        val logging2 = HttpLoggingInterceptor()

        if(BuildConfig.DEBUG) {
            logging.level = HttpLoggingInterceptor.Level.HEADERS
            logging2.level = HttpLoggingInterceptor.Level.BODY
        }else{
            logging.level = HttpLoggingInterceptor.Level.NONE
            logging2.level = HttpLoggingInterceptor.Level.NONE
        }

        authOkHttpClient = OkHttpClient.Builder()
                .readTimeout(30, TimeUnit.SECONDS)
                .connectTimeout(30, TimeUnit.SECONDS)
                .writeTimeout(30, TimeUnit.SECONDS)
                .addInterceptor(logging)
                .addInterceptor(logging2)
                .build()

        retrofit = Retrofit.Builder()
                .baseUrl(baseUrl)
                .client(authOkHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()

        apiService = retrofit.create(clss)
    }
}
