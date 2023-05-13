package com.sooq.open.feature.domain.usecase

import com.sooq.open.feature.data.localdata.dynamic.FilterData
import com.sooq.open.feature.domain.FeatureRepository
import javax.inject.Inject

class GetFilterDataUseCase @Inject constructor(
    private val repository: FeatureRepository
) {

    suspend operator fun invoke(id:Int):List<FilterData>{
        return repository.getFilterField(id)
    }
}