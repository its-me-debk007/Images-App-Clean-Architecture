package `in`.images.app.domain.repository

import `in`.images.app.domain.model.ImageModel
import kotlinx.coroutines.flow.Flow

interface ImageRepository {

    suspend fun getImage(query: String): Flow<List<ImageModel>>
}
