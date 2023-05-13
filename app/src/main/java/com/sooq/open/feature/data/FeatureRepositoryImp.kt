package com.sooq.open.feature.data

import com.sooq.open.feature.data.localdata.CategoryDataLocal
import com.sooq.open.feature.data.localdata.CategoryLocalModel
import com.sooq.open.feature.data.localdata.SubCategoryLocalModel
import com.sooq.open.feature.data.localdata.dynamic.FilterData
import com.sooq.open.feature.data.localdata.dynamic.Option.DynamicOption
import com.sooq.open.feature.data.localdata.dynamic.Option.Field
import com.sooq.open.feature.data.localdata.dynamic.Option.Option
import com.sooq.open.feature.data.localdata.dynamic.assign.DynamicAssign
import com.sooq.open.feature.data.localdata.dynamic.assign.FieldsLabel
import com.sooq.open.feature.data.localdata.dynamic.assign.SearchFlow
import com.sooq.open.feature.data.mapper.CategoryModelMapper
import com.sooq.open.feature.data.mapper.SubCategoryModelMapper
import com.sooq.open.feature.domain.FeatureRepository
import com.sooq.open.feature.domain.model.CategoryModel
import com.sooq.open.feature.domain.model.SubCategoryModel
import io.realm.Realm
import io.realm.kotlin.executeTransactionAwait
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext
import javax.inject.Inject

class FeatureRepositoryImp @Inject constructor(
    private val categoryModelMapper: CategoryModelMapper,
    private val subCategoryModelMapper: SubCategoryModelMapper
) : FeatureRepository {

    private val realm by lazy {
        Realm.getDefaultInstance()
    }

    override suspend fun insertDataToLocal(categoryLocalModel: CategoryDataLocal) {
        if (realm.isEmpty) {
            realm.executeTransactionAwait(Dispatchers.IO) { realmTransaction ->
                realmTransaction.insert(categoryLocalModel)
            }
        }
    }

    override suspend fun getCategories(): List<CategoryModel> {
        val categoryList = mutableListOf<CategoryModel>()
        realm.executeTransactionAwait(Dispatchers.IO) { realmTransaction ->
            categoryList.addAll(
                realmTransaction.where(CategoryLocalModel::class.java).findAll()
                    .map {
                        categoryModelMapper.map(it)
                    }
            )
        }
        return categoryList
    }

    override suspend fun getSubCategories(categoryId: Int): List<SubCategoryModel> {
        val subCategoryList = mutableListOf<SubCategoryModel>()
        realm.executeTransactionAwait(Dispatchers.IO) { realmTransaction ->
            subCategoryList.addAll(
                realmTransaction.where(SubCategoryLocalModel::class.java)
                    .equalTo("parentId", categoryId).findAll()
                    .map {
                        subCategoryModelMapper.map(it)
                    }
            )
        }
        return subCategoryList
    }

    override suspend fun insertAssign(dynamicAssign: DynamicAssign) {
        realm.executeTransactionAwait(Dispatchers.IO) { realmTransaction ->
            realmTransaction.insert(dynamicAssign)
        }
    }

    override suspend fun insertOption(dynamicOption: DynamicOption) {
        realm.executeTransactionAwait(Dispatchers.IO) { realmTransaction ->
            realmTransaction.insert(dynamicOption)
        }
    }

    override suspend fun getFilterField(categoryId: Int): List<FilterData> {
        val orderList = mutableListOf<String>()
        realm.executeTransactionAwait(Dispatchers.IO) { realmTransaction ->
            realmTransaction.where(SearchFlow::class.java)
                .equalTo("categoryId", categoryId).findFirst()?.order?.let {
                    orderList.addAll(
                        it
                    )
                }
        }
        return getFields(orderList)
    }

    private suspend fun getFields(order: List<String>): List<FilterData> {
        val fieldList = mutableListOf<Field>()
        realm.executeTransactionAwait(Dispatchers.IO) { realmTransaction ->
            order.forEach { it ->
                realmTransaction.where(Field::class.java)
                    .equalTo("name", it).findFirst()?.let { field ->
                        fieldList.add(field)
                    }
            }

        }
        return getFieldsLabel(fieldList)
    }

    private suspend fun getFieldsLabel(fields: List<Field>): List<FilterData> {
        var fieldLabel: FieldsLabel? = null
        val optionsList = mutableListOf<Option>()
        val filterDataList = mutableListOf<FilterData>()
        realm.executeTransactionAwait(Dispatchers.IO) { realmTransaction ->
            fields.forEach { field ->
                realmTransaction.where(FieldsLabel::class.java).equalTo("fieldName", field.name)
                    .findFirst()?.let { fieldLable ->
                        fieldLabel = fieldLable
                    }
            }
        }
        realm.executeTransactionAwait(Dispatchers.IO) { realmTransaction ->
            fields.forEach { field ->
                realmTransaction.where(Option::class.java).equalTo("fieldId", field.id).findAll()
                    ?.let { options ->
                        optionsList.addAll(options)
                    }
                filterDataList.add(
                    FilterData(
                        field = field,
                        fieldLabel = fieldLabel,
                        option = optionsList
                    )
                )
            }
        }

        return filterDataList
    }
}