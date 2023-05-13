package com.sooq.open.feature.domain.usecase

import com.sooq.open.feature.domain.FeatureRepository
import com.sooq.open.feature.domain.model.SubCategoryModel
import javax.inject.Inject

class GetSubCategoryUseCase @Inject constructor(
    private val repository: FeatureRepository
) {

    suspend operator fun invoke(id: Int): List<SubCategoryModel> {
        return repository.getSubCategories(id)
    }
}