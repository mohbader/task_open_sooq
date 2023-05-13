package com.sooq.open.feature.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sooq.open.feature.data.localdata.CategoryDataLocal
import com.sooq.open.feature.data.localdata.CategoryLocalModel
import com.sooq.open.feature.data.localdata.dynamic.Option.DynamicOption
import com.sooq.open.feature.data.localdata.dynamic.assign.DynamicAssign
import com.sooq.open.feature.domain.usecase.InsertAssignUseCase
import com.sooq.open.feature.domain.usecase.InsertDataToLocalUseCase
import com.sooq.open.feature.domain.usecase.InsertOptionUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val insertUserCase: InsertDataToLocalUseCase,
    private val insertOptionUseCase: InsertOptionUseCase,
    private val insertAssignUseCase: InsertAssignUseCase
) : ViewModel() {

    fun insertLocalData(categoryLocalDate: CategoryDataLocal) {
        viewModelScope.launch {
            try {
                insertUserCase(categoryLocalDate)
            } catch (e: Exception) {
                e.message
            }
        }
    }

    fun insertAssign(dynamicAssign: DynamicAssign) {
        viewModelScope.launch {
            try {
                insertAssignUseCase(dynamicAssign)
            } catch (e: Exception) {
                e.message
            }
        }
    }

    fun insertOption(dynamicOption: DynamicOption) {
        viewModelScope.launch {
            try {
                insertOptionUseCase(dynamicOption)
            } catch (e: Exception) {
                e.message
            }
        }
    }

}