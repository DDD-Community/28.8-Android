package com.ddd.carssok.core.network.di

import com.ddd.carssok.core.network.AuthInterceptor
import com.ddd.carssok.core.network.BuildConfig
import com.ddd.carssok.core.network.NetworkResultCallAdapterFactory
import com.ddd.carssok.datastore.CarssokDataStore
import com.facebook.flipper.plugins.network.FlipperOkhttpInterceptor
import com.facebook.flipper.plugins.network.NetworkFlipperPlugin
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Singleton
    @Provides
    fun provideNetworkResultCallAdapterFactory() = NetworkResultCallAdapterFactory()

    @Singleton
    @Provides
    fun provideAuthInterceptor(dataStore: CarssokDataStore) = AuthInterceptor(dataStore)

    @Singleton
    @Provides
    fun provideOkHttpClient(authInterceptor: AuthInterceptor): OkHttpClient {
        val okHttpBuilder = OkHttpClient.Builder().apply {
            if (BuildConfig.DEBUG) {
                val loggingInterceptor = HttpLoggingInterceptor().apply {
                    setLevel(HttpLoggingInterceptor.Level.BODY)
                }
                val flipper = FlipperOkhttpInterceptor(NetworkFlipperPlugin())
                addInterceptor(loggingInterceptor)
                addNetworkInterceptor(flipper)
                build()
            }
        }
        return okHttpBuilder.addInterceptor(authInterceptor).build()
    }

    @Singleton
    @Provides
    fun provideRetrofit(
        okHttpClient: OkHttpClient,
        networkResultCallAdapterFactory: NetworkResultCallAdapterFactory
    ): Retrofit {
        return Retrofit.Builder()
            .client(okHttpClient)
            .baseUrl(BuildConfig.CARSSOK_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(networkResultCallAdapterFactory)
            .build()
    }
}