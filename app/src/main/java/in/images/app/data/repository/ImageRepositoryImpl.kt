package `in`.images.app.data.repository

import `in`.images.app.data.network.ApiService
import `in`.images.app.data.toImageModel
import `in`.images.app.domain.model.ImageModel
import `in`.images.app.domain.repository.ImageRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class ImageRepositoryImpl @Inject constructor(private val apiService: ApiService) :
    ImageRepository {

    override suspend fun getImage(query: String): Flow<List<ImageModel>> = flow {
        emit(apiService.getImages(query = query).hits.map { it.toImageModel() })
    }
}
