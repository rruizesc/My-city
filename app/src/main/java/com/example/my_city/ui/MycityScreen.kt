package com.example.my_city.ui

import MycityViewModel
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.calculateEndPadding
import androidx.compose.foundation.layout.calculateStartPadding
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.my_city.R
import com.example.my_city.model.City
import com.example.my_city.utils.CityContentType


@Composable
fun MycityApp(
    windowSize: WindowWidthSizeClass,
    onBackPressed: () -> Unit,
) {
    val viewModel: MycityViewModel = viewModel()
    val uiState by viewModel.uiState.collectAsState()
    val contentType = when (windowSize) {
        WindowWidthSizeClass.Compact,
        WindowWidthSizeClass.Medium -> CityContentType.ListOnly

        WindowWidthSizeClass.Expanded -> CityContentType.ListAndDetail
        else -> CityContentType.ListOnly
    }

    Scaffold(
        topBar = {
            CityAppBar(
                isShowingListPage = uiState.isShowingListPage,
                onBackButtonClick = { viewModel.navigateToListPage() },
                windowSize = windowSize
            )
        }
    ) { innerPadding ->
        if (contentType == CityContentType.ListAndDetail) {
            CityListAndDetail(
                city = uiState.cityCategories,
                selectedCity = uiState.currentCategory,
                onClick = {
                    viewModel.updateCurrentCategory(it)
                },
                onBackPressed = onBackPressed,
                contentPadding = innerPadding,
                modifier = Modifier.fillMaxWidth()
            )
        } else {
            if (uiState.isShowingListPage) {
                CityList(
                    city = uiState.cityCategories,
                    onClick = {
                        viewModel.updateCurrentCategory(it)
                        viewModel.navigateToDetailPage()
                    },
                    modifier = Modifier.padding(horizontal = dimensionResource(R.dimen.padding_medium)),
                    contentPadding = innerPadding,
                )
            } else {
                CityDetail(
                    selectedCity = uiState.currentCategory,
                    contentPadding = innerPadding,
                    onBackPressed = {
                        viewModel.navigateToListPage()
                    }
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CityAppBar(
    onBackButtonClick: () -> Unit,
    isShowingListPage: Boolean,
    windowSize: WindowWidthSizeClass,
    modifier: Modifier = Modifier
) {
    val isShowingDetailPage = windowSize != WindowWidthSizeClass.Expanded && !isShowingListPage
    TopAppBar(
        title = {
            Text(
                text = if (isShowingDetailPage) {
                    stringResource(R.string.detail_fragment_label)
                } else {
                    stringResource(R.string.list_fragment_label)
                },
                fontWeight = FontWeight.Bold
            )
        },
        navigationIcon = if (isShowingDetailPage) {
            {
                IconButton(onClick = onBackButtonClick) {
                    Icon(
                        imageVector = Icons.Filled.ArrowBack,
                        contentDescription = stringResource(R.string.back_button)
                    )
                }
            }
        } else {
            { Box {} }
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.primary
        ),
        modifier = modifier,
    )
}


@Composable
private fun CityListItem(
    city: City,
    onItemClick: (City) -> Unit,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = dimensionResource(R.dimen.padding_medium), vertical = dimensionResource(R.dimen.padding_small))
            .clickable { onItemClick(city) }
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            CityListImageItem(city = city, modifier = Modifier.size(64.dp))
            Column(modifier = Modifier.padding(start = 16.dp)) {
                Text(
                    text = stringResource(city.titleResourceId),
                    style = MaterialTheme.typography.bodyLarge,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(bottom = 4.dp)
                )
                Text(
                    text = stringResource(city.subtitleResourceId),
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.secondary
                )
            }
        }
    }
}

@Composable
private fun CityListImageItem(city: City, modifier: Modifier = Modifier) {
    Image(
        painter = painterResource(id = city.imageResourceId),
        contentDescription = null,
        modifier = modifier
            .size(64.dp)
            .clip(RoundedCornerShape(8.dp)),
        contentScale = ContentScale.Crop
    )
}


@Composable
private fun CityList(
    city: List<City>,
    onClick: (City) -> Unit,
    modifier: Modifier = Modifier,
    contentPadding: PaddingValues = PaddingValues(0.dp),
) {
    LazyColumn(
        contentPadding = contentPadding,
        verticalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.padding_medium)),
        modifier = modifier.padding(top = dimensionResource(R.dimen.padding_medium)),
    ) {
        items(city, key = { city -> city.id }) { cityItem ->
            CityListItem(
                city = cityItem,
                onItemClick = onClick
            )
        }
    }
}


@Composable
private fun CityDetail(
    selectedCity: City,
    onBackPressed: () -> Unit,
    contentPadding: PaddingValues,
    modifier: Modifier = Modifier
) {
    BackHandler {
        onBackPressed()
    }
    val scrollState = rememberScrollState()
    val layoutDirection = LocalLayoutDirection.current
    Box(
        modifier = modifier
            .verticalScroll(state = scrollState)
            .padding(top = contentPadding.calculateTopPadding())
    ) {
        Column(
            modifier = Modifier
                .padding(
                    bottom = contentPadding.calculateTopPadding(),
                    start = contentPadding.calculateStartPadding(layoutDirection),
                    end = contentPadding.calculateEndPadding(layoutDirection)
                )
        ) {
            // ... (contenido de los detalles de la ciudad)
        }
    }
}

@Composable
private fun CityListAndDetail(
    city: List<City>,
    selectedCity: City,
    onClick: (City) -> Unit,
    onBackPressed: () -> Unit,
    modifier: Modifier = Modifier,
    contentPadding: PaddingValues = PaddingValues(0.dp),
) {
    Row(modifier = modifier) {
        CityList(
            city = city,
            onClick = onClick,
            modifier = Modifier.weight(2f),
            contentPadding = contentPadding
        )
        CityDetail(
            selectedCity = selectedCity,
            onBackPressed = onBackPressed,
            modifier = Modifier.weight(3f),
            contentPadding = contentPadding
        )
    }
}
