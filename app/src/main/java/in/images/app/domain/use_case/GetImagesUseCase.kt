package `in`.images.app.domain.use_case

import `in`.images.app.common.ApiResponse
import `in`.images.app.domain.model.ImageModel
import `in`.images.app.domain.repository.ImageRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class GetImagesUseCase(
    private val imageRepository: ImageRepository
) {

    operator fun invoke(query: String): Flow<ApiResponse<List<ImageModel>>> = flow {
        emit(ApiResponse.Loading())

        try {
            emit(ApiResponse.Success(data = imageRepository.getImages(query)))

        } catch (e: java.lang.Exception) {
            emit(ApiResponse.Error(msg = e.message.toString()))
        }
    }
}
