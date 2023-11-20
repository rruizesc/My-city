package com.example.my_city.ui

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun OptionListScreen(
    viewModel: MycityViewModel = viewModel(),
    windowSize: WindowWidthSizeClass,
    onBackPressed: () -> Unit,
    onSendButtonClicked: (String) -> Unit = {}

) {

}