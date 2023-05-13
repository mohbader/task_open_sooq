package com.sooq.open.feature.domain.usecase

import com.sooq.open.feature.data.localdata.dynamic.Option.DynamicOption
import com.sooq.open.feature.domain.FeatureRepository
import javax.inject.Inject

class InsertOptionUseCase @Inject constructor(
    private val repository: FeatureRepository
) {

    suspend operator fun invoke(dynamicOption: DynamicOption) {
        repository.insertOption(dynamicOption)
    }
}