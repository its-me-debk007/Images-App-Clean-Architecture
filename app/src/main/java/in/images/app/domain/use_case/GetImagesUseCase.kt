package `in`.images.app.domain.use_case

import `in`.images.app.common.ApiState
import `in`.images.app.domain.model.ImageModel
import `in`.images.app.domain.repository.ImageRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class GetImagesUseCase(
    private val imageRepository: ImageRepository
) {

    operator fun invoke(query: String): Flow<ApiState<List<ImageModel>>> = flow {
        emit(ApiState.Loading())

        try {
            emit(ApiState.Success(data = imageRepository.getImages(query)))

        } catch (e: java.lang.Exception) {
            emit(ApiState.Error(msg = e.message.toString()))
        }
    }
}
