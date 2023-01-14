package `in`.images.app.presentation.screens

import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import `in`.images.app.common.ApiState
import `in`.images.app.presentation.viewmodel.ImageViewModel

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun HomeScreen(viewModel: ImageViewModel = hiltViewModel()) {
    val result = viewModel.imageList.value

    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier.fillMaxSize()
    ) {
        when (result) {
            is ApiState.Loading -> {

                CircularProgressIndicator()
            }

            is ApiState.Error -> {

                Text(text = result.errorMsg.toString())
            }

            is ApiState.Success -> {
                Log.d("API_Result", result.data.toString())

                LazyColumn(modifier = Modifier.fillMaxSize()) {
                    items(result.data!!.size) {

                        GlideImage(
                            model = result.data[it].imageUrl,
                            contentDescription = null,
                            modifier = Modifier.fillMaxWidth()
                                .padding(8.dp)
                        )
                    }
                }
            }
        }
    }
}
