package com.loud9ja.loud9ja.di

import com.loud9ja.loud9ja.network.LoudAPI
import com.loud9ja.loud9ja.repository.AuthRepository
import com.loud9ja.loud9ja.repository.AuthRepositoryImpl
import com.loud9ja.loud9ja.repository.ProfileRepository
import com.loud9ja.loud9ja.repository.ReportRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {

    @Singleton
    @Provides
    fun provideAuthRepository(api: LoudAPI): AuthRepository {
        return AuthRepositoryImpl(api)
    }

    @Singleton
    @Provides
    fun provideProfileRepository(api: LoudAPI): ProfileRepository {
        return ProfileRepository(api)
    }

    @Singleton
    @Provides
    fun provideReportRepository(api: LoudAPI): ReportRepository {
        return ReportRepository(api)
    }
}