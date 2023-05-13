package com.sooq.open.feature.domain.usecase

import com.sooq.open.feature.domain.FeatureRepository
import com.sooq.open.feature.domain.model.CategoryModel
import javax.inject.Inject

class GetCategoryUseCase @Inject constructor(
    private val repository: FeatureRepository
) {

    suspend operator fun invoke(): List<CategoryModel> {
        return repository.getCategories()
    }
}