package com.adyen.android.assignment.ui

import android.Manifest
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.adyen.android.assignment.R
import com.adyen.android.assignment.domain.model.Place
import com.adyen.android.assignment.ui.model.PlacesScreenUiState
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage

@Composable
fun PlacesListScreen(
    uiState: PlacesScreenUiState,
    modifier: Modifier = Modifier,
    onGetCurrentLocationSuccess: (Double, Double) -> Unit = { lat, lng -> },
    onGetCurrentLocationFailed: () -> Unit = {},
    onPermissionDenied: () -> Unit = {}
) {
    val permissions = arrayOf(Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION)
    val context = LocalContext.current
    val requestPermissionLauncher = rememberLauncherForActivityResult(ActivityResultContracts.RequestMultiplePermissions()) { grantedResults ->
        if (grantedResults.values.reduce { acc, b -> acc && b  }) {
            LocationProvider.getCurrentLocation(
                context,
                onGetCurrentLocationSuccess = onGetCurrentLocationSuccess,
                onGetCurrentLocationFailed = {
                    onGetCurrentLocationFailed()
                }
            )
        } else {
            onPermissionDenied()
        }
    }
    LaunchedEffect(key1 = Unit) {
        requestPermissionLauncher.launch(permissions)
    }
    Scaffold(topBar = { AppBar() }) { paddingValues ->
        Box(
            modifier = modifier
                .padding(paddingValues)
                .fillMaxSize()
        ) {
            if(uiState.error != null) {
                ErrorContent(uiState.error)
            } else if(uiState.isLoading) {
                CircularProgressIndicator(
                    modifier = Modifier.align(Alignment.Center)
                )
            } else {
                PlacesListContent(
                    data = uiState.data
                )
            }
        }
    }
}

@Composable
private fun ErrorContent(error: String) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Image(
            modifier = Modifier.size(50.dp),
            painter = painterResource(id = R.drawable.warning),
            contentDescription = null
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(text = error)
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun AppBar(modifier: Modifier = Modifier) {
    Surface(
        modifier = modifier,
        shadowElevation = 3.dp
    ) {
        TopAppBar(
            title = { Text(text = stringResource(id = R.string.places_screen_page_title)) },
        )
    }
}

@Composable
private fun PlacesListContent(
    data: List<Place>,
    modifier: Modifier = Modifier,

) {
    LazyColumn(modifier = modifier) {
        items(data) { item ->
            PhoneListItem(item = item)
        }
    }
}

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
private fun PhoneListItem(
    item: Place,
    modifier: Modifier = Modifier,
) {
    Column {
        Row(
            modifier = modifier
                .padding(16.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            GlideImage(
                model = item.picture,
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(80.dp)
                    .aspectRatio(1f) // Ensures the image is square
            )
            Column(
                modifier = Modifier
                    .fillMaxHeight()
                    .padding(vertical = 4.dp)
            ) {
                Text(
                    text = item.name,
                    style = MaterialTheme.typography.titleMedium
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = item.description,
                    style = MaterialTheme.typography.bodyMedium
                )
            }
        }
        HorizontalDivider(
            color = Color.Gray,
            thickness = (0.2).dp
        )
    }
}


@Preview
@Composable
private fun PlacesListScreenLoadingPreview() {
    MaterialTheme {
        Surface {
            PlacesListScreen(
                uiState = PlacesScreenUiState(
                    isLoading = true
                )
            )
        }
    }
}

@Preview
@Composable
private fun PlacesListScreenDataPreview() {
    MaterialTheme {
        Surface {
            PlacesListScreen(
                uiState = PlacesScreenUiState(
                    data = (1..10).map {
                        Place(
                            name = "Name",
                            description = "Description",
                            picture = "picture"
                        )
                    }
                )
            )
        }
    }
}

@Preview
@Composable
private fun PlacesListScreenErrorPreview() {
    MaterialTheme {
        Surface {
            PlacesListScreen(
                uiState = PlacesScreenUiState(
                    error = "Something went wrong"
                )
            )
        }
    }
}