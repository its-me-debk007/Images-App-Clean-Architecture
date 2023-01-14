package `in`.images.app.data.repository

import `in`.images.app.data.network.ApiService
import `in`.images.app.data.toImageModel
import `in`.images.app.domain.model.ImageModel
import `in`.images.app.domain.repository.ImageRepository
import javax.inject.Inject

class ImageRepositoryImpl @Inject constructor(private val apiService: ApiService) :
    ImageRepository {

    override suspend fun getImages(query: String): List<ImageModel> =
        apiService.getImages(query = query).hits.map { it.toImageModel() }
}
