package com.adyen.android.assignment.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.collectAsState
import com.adyen.android.assignment.ui.viewmodel.PlacesViewModel
import dagger.hilt.android.AndroidEntryPoint
import androidx.compose.runtime.getValue

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val viewModel by viewModels<PlacesViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                val uiState by viewModel.uiState.collectAsState()
                PlacesListScreen(
                    uiState = uiState,
                    onGetCurrentLocationSuccess = viewModel::fetchNearByPlaces,
                    onGetCurrentLocationFailed = viewModel::onGetCurrentLocationFailed,
                    onPermissionDenied = viewModel::onPermissionDenied,
                )
            }
        }
    }
}
