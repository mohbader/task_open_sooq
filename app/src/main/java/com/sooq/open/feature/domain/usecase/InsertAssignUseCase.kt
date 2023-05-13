package com.sooq.open.feature.domain.usecase

import com.sooq.open.feature.data.localdata.dynamic.assign.DynamicAssign
import com.sooq.open.feature.domain.FeatureRepository
import javax.inject.Inject

class InsertAssignUseCase @Inject constructor(
    private val repository: FeatureRepository
) {

    suspend operator fun invoke(dynamicAssign: DynamicAssign){
        repository.insertAssign(dynamicAssign)
    }
}