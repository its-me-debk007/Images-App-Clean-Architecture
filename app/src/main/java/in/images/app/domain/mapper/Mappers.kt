package `in`.images.app.domain.mapper

import `in`.images.app.data.model.HitDTO
import `in`.images.app.domain.model.ImageModel

fun HitDTO.toImageModel() = ImageModel(
    imageUrl = this.previewURL
)
