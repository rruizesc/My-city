import androidx.lifecycle.ViewModel
import com.example.my_city.R
import com.example.my_city.data.LocalCityDataProvider
import com.example.my_city.model.City
import com.example.my_city.model.SubCity
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update

class MycityViewModel : ViewModel() {

    private val cityCategories = LocalCityDataProvider.getCityData().distinctBy { it.titleResourceId }

    private val subCityByCategory = mapOf(
        cityCategories[0] to listOf(
            SubCity(1, R.string.casaDeCampo,R.drawable.CasaCampo),
            SubCity(2, R.string.Retiro,R.drawable.Retiro),
            SubCity(3, R.string.JuanCarlos1,R.drawable.JuanCarlosI)
        ),
        cityCategories[1] to listOf(
            SubCity(1, R.string.Islazul,R.drawable.IslaZul),
            SubCity(2, R.string.LaVaguada,R.drawable.Vaguada),
            SubCity(3, R.string.LaVaguada,R.drawable.Vaguada)
        ),
        cityCategories[2] to listOf(
            SubCity(1, R.string.RatonPerez,R.drawable.MuseoRatonPerez),
            SubCity(2, R.string.TourBernabeu,R.drawable.MejorEstadioDdelMundo),
            SubCity(3, R.string.ParqueDeAtracciones,R.drawable.ParqueDeAtracciones)
        ),
        cityCategories[3] to listOf(
            SubCity(1, R.string.MordidaBernabeu,R.drawable.LaMordidaBernabeu),
            SubCity(2, R.string.LaEsquinaDelReal,R.drawable.LaEsquina),
            SubCity(3, R.string.Sakana,R.drawable.Sakana)
        ),

    )

    private val _uiState = MutableStateFlow(
        CityUiState(
            cityCategories = cityCategories,
            currentCategory = cityCategories.first(),
            currentSubCategory = subCityByCategory[cityCategories.first()]?.firstOrNull(),
            isShowingListPage = true
        )
    )
    val uiState: StateFlow<CityUiState> = _uiState

    fun updateCurrentCategory(selectedCategory: City) {
        _uiState.update {
            it.copy(
                currentCategory = selectedCategory,
                currentSubCategory = subCityByCategory[selectedCategory]?.firstOrNull(),
                isShowingListPage = true
            )
        }
    }

    fun updateCurrentSubCategory(selectedSubCategory: SubCity) {
        _uiState.update {
            it.copy(
                currentSubCategory = selectedSubCategory,
                isShowingListPage = false
            )
        }
    }

    fun navigateToListPage() {
        _uiState.update {
            it.copy(isShowingListPage = true)
        }
    }

    fun navigateToDetailPage() {
        _uiState.update {
            it.copy(isShowingListPage = false)
        }
    }
}

data class CityUiState(
    val cityCategories: List<City>,
    val currentCategory: City,
    val currentSubCategory: SubCity?,
    val isShowingListPage: Boolean
)
