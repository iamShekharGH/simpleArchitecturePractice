package shekharhandigol.practice.di

import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.ktor.client.HttpClient
import io.ktor.http.URLProtocol
import shekharhandigol.loginapp.network.LoginAppHttpClientBuilder
import shekharhandigol.loginapp.network.RequestHandler


@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

    fun providesHttpClient(): HttpClient =
        LoginAppHttpClientBuilder().protocol(URLProtocol.HTTPS).host("").build()

    fun providesRequestHandler(httpClient: HttpClient): RequestHandler = RequestHandler(httpClient)


}