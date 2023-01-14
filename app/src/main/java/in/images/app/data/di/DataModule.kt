package `in`.images.app.data.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import `in`.images.app.BuildConfig
import `in`.images.app.data.network.ApiService
import `in`.images.app.data.repository.ImageRepositoryImpl
import `in`.images.app.domain.repository.ImageRepository
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object DataModule {

    @Provides
    @Singleton
    fun provideApiService(): ApiService = Retrofit.Builder()
        .baseUrl(BuildConfig.BASE_URL)
        .addConverterFactory(MoshiConverterFactory.create())
        .build()
        .create(ApiService::class.java)

    @Provides
    @Singleton
    fun providesImageRepository(apiService: ApiService) : ImageRepository = ImageRepositoryImpl(apiService)
}
