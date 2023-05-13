package com.sooq.open.feature.data.mapper

import com.sooq.open.feature.data.localdata.CategoryLocalModel
import com.sooq.open.feature.domain.model.CategoryModel
import com.sooq.open.helper.MapperHelper
import javax.inject.Inject

class CategoryModelMapper @Inject constructor(
//    private val subCategoryModelMapper: SubCategoryModelMapper
) : MapperHelper<CategoryLocalModel, CategoryModel>() {

    override fun map(source: CategoryLocalModel): CategoryModel {
        return CategoryModel(
            icon = source.icon,
            name = source.labelEn,
            order = source.order,
            label = source.label,
            id = source.id
        )
    }
}