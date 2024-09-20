package shekharhandigol.practice.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import shekharhandigol.loginapp.features.auth.data.AuthRepository
import shekharhandigol.loginapp.features.auth.data.AuthRepositoryImpl
import shekharhandigol.loginapp.features.auth.data.UserRepository
import shekharhandigol.loginapp.features.auth.data.UserRepositoryImpl

@Module
@InstallIn(ViewModelComponent::class)
class AuthModule {

    @Provides
    fun providesAuthRepository(impl: AuthRepositoryImpl): AuthRepository = impl

    @Provides
    fun providesUserRepository(impl: UserRepositoryImpl): UserRepository = impl
}