package `in`.images.app.domain.repository

import `in`.images.app.domain.model.ImageModel

interface ImageRepository {

    suspend fun getImages(query: String): List<ImageModel>
}
