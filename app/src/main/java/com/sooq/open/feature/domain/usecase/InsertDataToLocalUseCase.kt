package com.sooq.open.feature.domain.usecase

import com.sooq.open.feature.data.localdata.CategoryDataLocal
import com.sooq.open.feature.data.localdata.CategoryLocalModel
import com.sooq.open.feature.domain.FeatureRepository
import javax.inject.Inject

class InsertDataToLocalUseCase @Inject constructor(
    private val repository: FeatureRepository
) {

    suspend operator fun invoke(categoryLocalModel: CategoryDataLocal) {
        repository.insertDataToLocal(categoryLocalModel)
    }
}