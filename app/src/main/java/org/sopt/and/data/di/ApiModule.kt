package org.sopt.and.data.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import org.sopt.and.data.service.AuthService
import org.sopt.and.data.service.MyPageService
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ApiModule {

    @Provides
    @Singleton
    fun provideAuthService(@ANDSOPT retrofit: Retrofit): AuthService =
        retrofit.create(AuthService::class.java)

    @Provides
    @Singleton
    fun provideMyPageService(@ANDSOPT retrofit: Retrofit): MyPageService =
        retrofit.create(MyPageService::class.java)
}
