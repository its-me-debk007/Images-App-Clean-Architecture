package `in`.images.app.data.network

import `in`.images.app.BuildConfig
import `in`.images.app.data.model.PixabayDTO
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET(".")
    suspend fun getImages(
        @Query("key") key : String = BuildConfig.API_KEY,
        @Query("q") query: String
    ): PixabayDTO


}
