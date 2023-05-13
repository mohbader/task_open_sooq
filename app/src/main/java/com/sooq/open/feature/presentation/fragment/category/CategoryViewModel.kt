package com.sooq.open.feature.presentation.fragment.category

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sooq.open.feature.domain.model.CategoryModel
import com.sooq.open.feature.domain.usecase.GetCategoryUseCase
import dagger.hilt.android.HiltAndroidApp
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CategoryViewModel @Inject constructor(
    private val useCase: GetCategoryUseCase
) : ViewModel() {

    private val _categoriesStateFlow: MutableStateFlow<List<CategoryModel>?> =
        MutableStateFlow(null)
    val categoriesStateFlow: MutableStateFlow<List<CategoryModel>?> = _categoriesStateFlow

    init {
        getCategories()
    }

    private fun getCategories() {
        viewModelScope.launch {
            _categoriesStateFlow.value = useCase()
        }
    }
}