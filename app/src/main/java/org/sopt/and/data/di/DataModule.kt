package org.sopt.and.data.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import org.sopt.and.domain.repository.LoginRepository
import org.sopt.and.domain.repository.MyPageRepository
import org.sopt.and.domain.repository.SignUpRepository
import org.sopt.and.data.repositoryimpl.LoginRepositoryImpl
import org.sopt.and.data.repositoryimpl.MyPageRepositoryImpl
import org.sopt.and.data.repositoryimpl.SignUpRepositoryImpl
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal abstract class DataModule {

    @Binds
    @Singleton
    abstract fun bindsSignUpRepo(
        repository: SignUpRepositoryImpl,
    ): SignUpRepository

    @Binds
    @Singleton
    abstract fun bindsLoginRepo(
        repository: LoginRepositoryImpl,
    ): LoginRepository

    @Binds
    @Singleton
    abstract fun bindsMyPageRepo(
        repository: MyPageRepositoryImpl,
    ): MyPageRepository
}
