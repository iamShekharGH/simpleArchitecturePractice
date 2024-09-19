package shekharhandigol.practice.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.ktor.client.HttpClient
import io.ktor.http.URLProtocol
import shekharhandigol.loginapp.network.LoginAppHttpClientBuilder
import shekharhandigol.loginapp.network.RequestHandler
import shekharhandigol.practice.BuildConfig


@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {


    @Provides
    fun providesHttpClient(): HttpClient =
        LoginAppHttpClientBuilder().protocol(URLProtocol.HTTP).host(BuildConfig.REST_URL).port(8080)
            .build()
    
    @Provides
    fun providesRequestHandler(httpClient: HttpClient): RequestHandler = RequestHandler(httpClient)


}