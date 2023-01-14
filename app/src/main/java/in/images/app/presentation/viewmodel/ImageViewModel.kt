package `in`.images.app.presentation.viewmodel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import `in`.images.app.common.ApiState
import `in`.images.app.domain.model.ImageModel
import `in`.images.app.domain.use_case.GetImagesUseCase
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class ImageViewModel @Inject constructor(private val getImagesUseCase: GetImagesUseCase) :
    ViewModel() {

    private val _imageList = mutableStateOf<ApiState<List<ImageModel>>>(ApiState.Loading())
    val imageList: State<ApiState<List<ImageModel>>> get() = _imageList

    init {
        getImages("dragon")
    }

    fun getImages(query: String) {
        getImagesUseCase(query).onEach {
            when (it) {
                is ApiState.Loading -> {
                    _imageList.value = ApiState.Loading()
                }
                is ApiState.Success -> {
                    _imageList.value = ApiState.Success(data = it.data)
                }
                is ApiState.Error -> {
                    _imageList.value = ApiState.Error(msg = it.errorMsg.toString())
                }
            }
        }.launchIn(viewModelScope)
    }
}
