package com.loud9ja.loud9ja.di

import com.loud9ja.loud9ja.domain.network.LoudAPI
import com.loud9ja.loud9ja.domain.repository.*
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

    @Singleton
    @Provides
    fun provideGroupRepository(api: LoudAPI): GroupRepository = GroupRepository(api)

    @Singleton
    @Provides
    fun providePostRepository(api: LoudAPI): PostsRepository = PostsRepositoryImpl(api)

    @Singleton
    @Provides
    fun providePollRepository(api: LoudAPI): PollRepository = PollRepository(api)
}