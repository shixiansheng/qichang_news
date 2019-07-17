package cn.abr.common.net


import android.os.Build


import java.util.concurrent.TimeUnit

import cn.abr.common.net.api.CacheProviders
import cn.abr.common.util.ConstantUtil
import cn.abr.inabr.net.api.APIService
import cn.abr.inabr.net.api.Api
import io.rx_cache2.internal.RxCache
import io.victoralbertos.jolyglot.GsonSpeaker
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class HttpUtil {

    companion object {
        val apis = Holder.apis
        val cacheProviders = Holder.cacheProviders
    }

    private object Holder {
        val apis: APIService
            get() {
                val retrofit = Retrofit.Builder()
                    .client(okHttpClient)
                    .baseUrl(Api.QCV_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .build()
                return retrofit.create(APIService::class.java)
            }

        val cacheProviders: CacheProviders
            get() {
                val cacheProviders = RxCache.Builder()
                    .useExpiredDataIfLoaderNotAvailable(true)
                    .persistence(ConstantUtil.getAPPContext().cacheDir, GsonSpeaker())
                    .using(CacheProviders::class.java)
                return cacheProviders
            }

        private const val DEFAULT_TIMEOUT: Long = 60


        private val logInterceptor: HttpLoggingInterceptor
            get() {
                val loggingInterceptor = HttpLoggingInterceptor()
                if (ConstantUtil.isAppDebug()) {
                    loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
                } else {
                    loggingInterceptor.level = HttpLoggingInterceptor.Level.NONE
                }
                return loggingInterceptor
            }


        private val okHttpClient: OkHttpClient
            get() = OkHttpClient.Builder()
                .addInterceptor { chain ->
                    val builder = chain.request().newBuilder()
                    builder.addHeader("Content-Type", "application/json;charset=UTF-8")
                    builder.addHeader("device_type", "ANDROID")
                        .addHeader("user_num", "")
                        .addHeader("phone_model", Build.BRAND + " " + Build.MODEL)
                        .addHeader("phone_os", Build.VERSION.RELEASE)
                        .addHeader("app_version", "")
                        .addHeader("app_channel", "_360")
                        .addHeader("app_id", "qichang")
                        .addHeader("device_id", "99000913326911")
                        .addHeader("Device", "99000913326911")
                        .addHeader("device_app_id", "")
                    chain.proceed(builder.build())
                }
                .addNetworkInterceptor(logInterceptor)
                .connectTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS)
                .writeTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS)
                .readTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS)
                .build()
    }
}
