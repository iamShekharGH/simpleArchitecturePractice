package shekharhandigol.practice.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import shekharhandigol.loginapp.features.auth.data.AuthRepository
import shekharhandigol.loginapp.features.auth.data.AuthRepositoryImpl

@Module
@InstallIn(ViewModelComponent::class)
class AuthModule {

    @Provides
    fun providesAuthRepository(impl: AuthRepositoryImpl): AuthRepository = impl
}