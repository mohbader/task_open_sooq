package com.sooq.open.feature.data.localdata


import com.google.gson.annotations.SerializedName
import io.realm.RealmList
import io.realm.RealmObject

open class CategoryDataLocal(
    @SerializedName("items")
    var categoryLocalModels: RealmList<CategoryLocalModel>?=null
) : RealmObject()