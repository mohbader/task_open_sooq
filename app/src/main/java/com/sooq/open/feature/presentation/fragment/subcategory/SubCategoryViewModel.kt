package com.sooq.open.feature.presentation.fragment.subcategory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sooq.open.feature.domain.model.SubCategoryModel
import com.sooq.open.feature.domain.usecase.GetSubCategoryUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SubCategoryViewModel @Inject constructor(
    private val useCase: GetSubCategoryUseCase
) : ViewModel() {

    private val _subCategoriesStateFlow: MutableStateFlow<List<SubCategoryModel>?> =
        MutableStateFlow(null)
    val subCategoriesStateFlow: MutableStateFlow<List<SubCategoryModel>?> = _subCategoriesStateFlow

     fun getSubCategory(id: Int) {
        viewModelScope.launch {
            try {
                _subCategoriesStateFlow.value = useCase(id)
            } catch (e: Exception) {
                e.message
            }
        }
    }
}