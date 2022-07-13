package com.loud9ja.loud9ja.di

import android.content.Context
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.loud9ja.loud9ja.Loud9ja
import com.loud9ja.loud9ja.domain.network.AuthInterceptor
import com.loud9ja.loud9ja.domain.network.LoudAPI
import com.loud9ja.loud9ja.utils.AuthPreference
import com.loud9ja.loud9ja.utils.Constants.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.Dispatcher
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {
    @Singleton
    @Provides
    fun provideGsonBuilder(): Gson {
        return GsonBuilder()
            //.excludeFieldsWithoutExposeAnnotation()
            .create()
    }

    @Singleton
    @Provides
    fun provideRetrofit(@ApplicationContext context: Context, gson: Gson): Retrofit.Builder {
        return Retrofit.Builder()
            .client(provideInterceptor(context))
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(gson))

    }

    @Singleton
    @Provides
    fun provideBluezoneService(retrofit: Retrofit.Builder): LoudAPI {
        return retrofit
            .build()
            .create(LoudAPI::class.java)
    }

    @Singleton
    @Provides
    fun provideAuthPreference(@ApplicationContext context: Context): AuthPreference {
        return AuthPreference(context)
    }

    @Singleton
    @Provides
    fun provideInterceptor(
        @ApplicationContext context: Context
    ): OkHttpClient {
        val dispatcher = Dispatcher()
        dispatcher.maxRequests = 1
        val interceptor =
            HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
        return OkHttpClient.Builder()
            .dispatcher(dispatcher)
            .dns(DnsSelector())
            .addInterceptor(AuthInterceptor(provideAuthPreference(context)))
            .addInterceptor(interceptor).build()
    }

    @Provides
    fun providesMainApplicationInstance(@ApplicationContext context: Context): Loud9ja {
        return context as Loud9ja
    }
}