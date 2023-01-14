package `in`.images.app.common

sealed class ApiResponse<T>(
    val data: T? = null,
    val errorMsg: String? = null
) {
    class Success<T>(data: T?) : ApiResponse<T>(data = data)

    class Error<T>(msg: String) : ApiResponse<T>(errorMsg = msg)

    class Loading<T> : ApiResponse<T>()
}
