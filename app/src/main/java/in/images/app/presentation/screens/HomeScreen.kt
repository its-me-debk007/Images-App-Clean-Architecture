package `in`.images.app.presentation.screens

import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import `in`.images.app.common.ApiState
import `in`.images.app.presentation.viewmodel.ImageViewModel

@OptIn(ExperimentalGlideComposeApi::class, ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(viewModel: ImageViewModel = hiltViewModel()) {
    val result = viewModel.imageList.value
    var query by remember { mutableStateOf("") }

    Column(Modifier.fillMaxSize()) {
        OutlinedTextField(
            value = query,
            onValueChange = {
                query = it
                viewModel.getImages(query.lowercase().trim())
            },
            modifier = Modifier.fillMaxWidth()
        )


        when (result) {
            is ApiState.Loading -> {
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier.fillMaxSize()
                ) {
                    CircularProgressIndicator()
                }
            }

            is ApiState.Error -> {
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier.fillMaxSize()
                ) {
                    Text(text = result.errorMsg.toString())
                }
            }

            is ApiState.Success -> {
                Log.d("API_Result", result.data.toString())

                LazyColumn(modifier = Modifier.fillMaxWidth()) {
                    items(result.data!!.size) {

                        GlideImage(
                            model = result.data[it].imageUrl,
                            contentDescription = null,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(8.dp)
                        )
                    }
                }
            }
        }
    }
}
