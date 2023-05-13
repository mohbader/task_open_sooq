package com.sooq.open.feature.domain

import com.sooq.open.feature.data.localdata.CategoryDataLocal
import com.sooq.open.feature.data.localdata.CategoryLocalModel
import com.sooq.open.feature.data.localdata.dynamic.FilterData
import com.sooq.open.feature.data.localdata.dynamic.Option.DynamicOption
import com.sooq.open.feature.data.localdata.dynamic.Option.Option
import com.sooq.open.feature.data.localdata.dynamic.assign.DynamicAssign
import com.sooq.open.feature.domain.model.CategoryModel
import com.sooq.open.feature.domain.model.SubCategoryModel

interface FeatureRepository {
    suspend fun insertDataToLocal(categoryLocalModel: CategoryDataLocal)
    suspend fun getCategories(): List<CategoryModel>
    suspend fun getSubCategories(categoryId: Int): List<SubCategoryModel>
    suspend fun insertAssign(dynamicAssign: DynamicAssign)
    suspend fun insertOption(dynamicOption: DynamicOption)
    suspend fun getFilterField(categoryId:Int):List<FilterData>

}