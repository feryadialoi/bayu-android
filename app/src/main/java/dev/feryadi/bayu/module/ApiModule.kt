package dev.feryadi.bayu.module

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dev.feryadi.bayu.apiservice.AuthApiService
import dev.feryadi.bayu.apiservice.UserBalanceApiService
import dev.feryadi.bayu.constant.Environment
import dev.feryadi.bayu.interceptor.UnauthorizedInterceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ApiModule {

    @Singleton
    @Provides
    fun okHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .readTimeout(60, TimeUnit.SECONDS)
            .connectTimeout(60, TimeUnit.SECONDS)
            .addInterceptor(UnauthorizedInterceptor())
            .build()
    }

    @Singleton
    @Provides
    fun gsonBuilder(): Gson {
        return GsonBuilder()
            .setLenient()
            .create()
    }

    @Singleton
    @Provides
    fun retrofit(client: OkHttpClient, gson: Gson): Retrofit {
        return Retrofit.Builder()
            .client(client)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .baseUrl(Environment.BASE_URL)
            .build()
    }

    @Singleton
    @Provides
    fun authApiService(retrofit: Retrofit): AuthApiService {
        return retrofit.create(AuthApiService::class.java)
    }

    @Singleton
    @Provides
    fun userBalanceApiService(retrofit: Retrofit): UserBalanceApiService {
        return retrofit.create(UserBalanceApiService::class.java)
    }
}