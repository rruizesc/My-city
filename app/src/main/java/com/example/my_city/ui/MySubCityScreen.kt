package com.example.my_city.ui

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.calculateEndPadding
import androidx.compose.foundation.layout.calculateStartPadding
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
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
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.my_city.R
import com.example.my_city.model.City
import com.example.my_city.model.Opcion
import com.example.my_city.utils.CityContentType

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun OptionListScreen(
    viewModel: MycityViewModel = viewModel(),
    windowSize: WindowWidthSizeClass,
    onBackPressed: () -> Unit

) {
    val cityUiState by viewModel.uiState.collectAsState()
    val contentType = when (windowSize) {
        WindowWidthSizeClass.Compact,
        WindowWidthSizeClass.Medium -> CityContentType.ListOnly

        WindowWidthSizeClass.Expanded -> CityContentType.ListAndDetail
        else -> CityContentType.ListOnly
    }

    Scaffold(
        topBar = {
            OptionListBar(
                isShowingListPage = cityUiState.isShowingListPage,
                onBackButtonClick = {
                    if (contentType == CityContentType.ListOnly && !cityUiState.isShowingListPage) {
                        viewModel.navegarAListaOpciones()
                    } else {
                        onBackPressed()
                    }
                },
                windowSize = windowSize,
                currentCity = cityUiState.cityActual,
                currentOption = cityUiState.opcionActual
            )
        }
    ) { innerPadding ->
        if (contentType == CityContentType.ListAndDetail) {
            OptionListAnDetail(
                options = cityUiState.cityActual.categories,
                opcionSeleccionada = cityUiState.opcionActual,
                onClick = {
                    viewModel.updateCurrentOpcion(it)
                },
                contentPadding = innerPadding,
                modifier = Modifier.fillMaxWidth()
            )
        } else {
            if(cityUiState.isShowingListPage){
                OptionsList(
                    options = cityUiState.cityActual.categories,
                    onClick = {
                        viewModel.updateCurrentOpcion(it)
                        viewModel.navegarADetallesOpcion()
                    },
                    contentPadding = innerPadding,
                    modifier = Modifier.padding(horizontal = dimensionResource(R.dimen.padding_medium))
                )
            } else{
                DetailOption(
                    selectedOption = cityUiState.opcionActual ,
                    contentPadding = innerPadding,
                    onBackPressed = { onBackPressed() })
            }
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun OptionListBar(
    isShowingListPage: Boolean,
    onBackButtonClick: () -> Unit,
    windowSize: WindowWidthSizeClass,
    currentCity: City,
    currentOption: Opcion,
    modifier: Modifier = Modifier
) {
    val isShowingDetailPage = windowSize != WindowWidthSizeClass.Expanded && !isShowingListPage
    Image(
        painter = painterResource(id = currentOption.imageResourceId),
        contentDescription = stringResource(id = R.string.category_banner),
        contentScale = ContentScale.Crop,
        modifier = Modifier
            .fillMaxWidth()
            .height(200.dp)
    )

    TopAppBar(
        title = {
            Text(
                text =
                if (isShowingDetailPage) {
                    stringResource(id = currentOption.titleResourceId)
                } else {
                    stringResource(id = currentCity.titleResourceId)
                },
                fontWeight = FontWeight.Thin
            )
        },
        navigationIcon = {
            IconButton(
                onClick = onBackButtonClick
            ) {
                Icon(
                    imageVector = Icons.Filled.KeyboardArrowLeft,
                    contentDescription = stringResource(R.string.flecha_volver)
                )
            }
        },
        colors = TopAppBarDefaults.mediumTopAppBarColors(
            containerColor = colorResource(R.color.white)
        ),
        modifier = modifier
    )
}

@Composable
fun OptionListAnDetail(
    modifier: Modifier = Modifier,
    options: List<Opcion>,
    opcionSeleccionada: Opcion,
    onClick: (Opcion) -> Unit,
    contentPadding: PaddingValues = PaddingValues(0.dp),
    viewModel: MycityViewModel = viewModel()
) {
    Row(
        modifier = modifier
    ) {
        OptionsList(
            options = options,
            onClick = onClick,
            contentPadding = contentPadding,
            modifier = Modifier
                .weight(2f)
                .padding(
                    horizontal = dimensionResource(R.dimen.padding_medium)
                )
        )

        DetailOption(
            selectedOption = opcionSeleccionada,
            modifier = Modifier.weight(3f),
            contentPadding = contentPadding,
            onBackPressed = {
                viewModel.navegarAListaOpciones()
            }

        )
    }
}

@Composable
fun OptionsList(
    modifier: Modifier = Modifier,
    options: List<Opcion>,
    onClick: (Opcion) -> Unit,
    contentPadding: PaddingValues = PaddingValues(0.dp)
) {
    LazyColumn(
        contentPadding = contentPadding,
        verticalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.padding_medium)),
        modifier = modifier
            .padding(top = dimensionResource(R.dimen.padding_small))
            .padding(bottom = dimensionResource(R.dimen.padding_small))
    ) {
        items(options, key = { opcion -> opcion.id }) { opcion ->
            OptionItem(
                opcion = opcion,
                onItemClick = onClick,
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun OptionItem(
    opcion: Opcion,
    onItemClick: (Opcion) -> Unit,
    modifier: Modifier = Modifier
) {


    Card(
        elevation = CardDefaults.cardElevation(),
        modifier = modifier,
        onClick = {
            onItemClick(opcion)
        }
    ) {
        Row(
            modifier = modifier
                .fillMaxWidth()
                .size(dimensionResource(R.dimen.card_image_height))
        ) {
            OpcionItemImage(
                opcion = opcion,
                modifier = Modifier.size(dimensionResource(R.dimen.card_image_height))
            )
            Column(
                modifier = Modifier
                    .padding(
                        vertical = dimensionResource(R.dimen.padding_small),
                        horizontal = dimensionResource(R.dimen.padding_medium)
                    )
                    .weight(1f)
            ) {
                Spacer(Modifier.height(5.dp))
                Text(
                    text = stringResource(opcion.titleResourceId),
                    style = MaterialTheme.typography.titleSmall,
                    fontSize = 30.sp,
                    modifier = Modifier.padding(bottom = dimensionResource(R.dimen.card_text_vertical_space))
                )
            }
        }
    }

}

@Composable
fun OpcionItemImage(
    opcion: Opcion,
    modifier: Modifier
) {
    Box(
        modifier = modifier
    ) {
        Image(
            painter = painterResource(opcion.imageResourceId),
            contentDescription = stringResource(opcion.titleResourceId),
            alignment = Alignment.Center,
            contentScale = ContentScale.FillHeight,
            modifier = Modifier.fillMaxSize()
        )
    }
}

@Composable
fun DetailOption(
    selectedOption: Opcion,
    contentPadding: PaddingValues,
    onBackPressed: () -> Unit,
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
            Image(
                painter = painterResource(selectedOption.imageResourceId),
                contentDescription = stringResource(selectedOption.titleResourceId),
                alignment = Alignment.TopCenter,
                contentScale = ContentScale.FillWidth
            )
        }
        Column (
            Modifier
                .align(Alignment.BottomStart)
                .fillMaxWidth()
                .background(
                    Brush.verticalGradient(
                        listOf(Color.Transparent, MaterialTheme.colorScheme.scrim),
                        0f,
                        400f
                    )
                )
        ){
            Text(
                text = stringResource(selectedOption.titleResourceId),
                style = MaterialTheme.typography.headlineLarge,
                color = MaterialTheme.colorScheme.inverseOnSurface,
                modifier = Modifier
                    .padding(horizontal = dimensionResource(R.dimen.padding_small))
            )
        }
    }
    Text(
        text = stringResource(selectedOption.detailTextId),
        style = MaterialTheme.typography.bodySmall,
        modifier = Modifier
            .padding(vertical = dimensionResource(R.dimen.padding_detail_content_vertical),
                horizontal = dimensionResource(R.dimen.padding_detail_content_horizontal))
    )
}


