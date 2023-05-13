package com.sooq.open.feature.presentation.fragment.filter

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sooq.open.feature.data.localdata.dynamic.FilterData
import com.sooq.open.feature.domain.model.SubCategoryModel
import com.sooq.open.feature.domain.usecase.GetFilterDataUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FilterViewModel @Inject constructor(
    private val getFilterDataUseCase: GetFilterDataUseCase
) : ViewModel() {

    private val _filterDataStateFlow: MutableStateFlow<List<FilterData>?> =
        MutableStateFlow(null)
    val filterDataStateFlow: MutableStateFlow<List<FilterData>?> = _filterDataStateFlow

    fun getFilterData(id: Int) {
        viewModelScope.launch {
            try {
                filterDataStateFlow.value = getFilterDataUseCase(id)
            } catch (e: Exception) {
                e.message
            }
        }
    }
}