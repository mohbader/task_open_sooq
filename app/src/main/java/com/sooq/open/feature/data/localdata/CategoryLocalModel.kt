package com.sooq.open.feature.data.localdata


import com.google.gson.annotations.SerializedName
import io.realm.RealmList
import io.realm.RealmObject
import io.realm.RealmResults
import io.realm.annotations.LinkingObjects
import io.realm.annotations.PrimaryKey
import io.realm.annotations.RealmClass

@RealmClass
open class CategoryLocalModel(

    @SerializedName("has_child")
    var hasChild: Int? = null,

    @SerializedName("icon")
    var icon: String? = null,

    @PrimaryKey
    @SerializedName("id")
    var id: Int? = null,

    @SerializedName("label")
    var label: String? = null,

    @SerializedName("label_ar")
    var labelAr: String? = null,

    @SerializedName("label_en")
    var labelEn: String? = null,

    @SerializedName("name")
    var name: String? = null,

    @SerializedName("order")
    var order: Int? = null,

    @SerializedName("parent_id")
    var parentId: Int? = null,

    @SerializedName("reporting_name")
    var reportingName: String? = null,

    @SerializedName("subCategories")
    var subCategories: RealmList<SubCategoryLocalModel>? = null

) : RealmObject()