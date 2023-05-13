package com.sooq.open.feature.domain.model

import com.sooq.open.feature.data.localdata.SubCategoryLocalModel
import com.sooq.open.helper.adapter.BaseSearchModel

data class CategoryModel(

    val icon: String?,

    val id: Int?,

    val label: String?,

    val name: String?,

    val order: Int?,
) : BaseSearchModel {
    override fun getSearchCriteria() = name.orEmpty()
}