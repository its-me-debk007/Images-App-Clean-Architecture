package `in`.images.app.domain.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import `in`.images.app.domain.repository.ImageRepository
import `in`.images.app.domain.use_case.GetImagesUseCase

@InstallIn(SingletonComponent::class)
@Module
object DomainModule {

    @Provides
    fun providesGetImagesUseCase(imageRepository: ImageRepository): GetImagesUseCase =
        GetImagesUseCase(imageRepository)
}
