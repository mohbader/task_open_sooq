package com.sooq.open.feature.data.mapper

import com.sooq.open.feature.data.localdata.SubCategoryLocalModel
import com.sooq.open.feature.domain.model.SubCategoryModel
import com.sooq.open.helper.MapperHelper
import javax.inject.Inject

class SubCategoryModelMapper @Inject constructor() :
    MapperHelper<SubCategoryLocalModel, SubCategoryModel>() {
    override fun map(source: SubCategoryLocalModel): SubCategoryModel {
        return SubCategoryModel(
            id = source.id,
            icon = source.icon,
            label = source.label,
            parentId = source.parentId,
            name = source.labelEn,
            order = source.order
        )
    }
}