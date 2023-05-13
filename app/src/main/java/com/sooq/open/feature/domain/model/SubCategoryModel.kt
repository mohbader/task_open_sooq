package com.sooq.open.feature.domain.model

import com.google.gson.annotations.SerializedName
import com.sooq.open.helper.adapter.BaseSearchModel
import io.realm.annotations.PrimaryKey

data class SubCategoryModel(
    val icon: String?,

    val id: Int?,

    val label: String?,

    val name: String?,

    val order: Int?,

    val parentId: Int?,
): BaseSearchModel {
    override fun getSearchCriteria() = name.orEmpty()
}