package com.sooq.open.feature.data.localdata.dynamic.Option


import com.google.gson.annotations.SerializedName
import io.realm.RealmObject

open class Option(
    @SerializedName("field_id")
    var fieldId: String? = null,
    @SerializedName("has_child")
    var hasChild: String? = null,
    @SerializedName("id")
    var id: String? = null,
    @SerializedName("label")
    var label: String? = null,
    @SerializedName("label_en")
    var labelEn: String? = null,
    @SerializedName("option_img")
    var optionImg: String? = null,
    @SerializedName("order")
    var order: String? = null,
    @SerializedName("parent_id")
    var parentId: String? = null,
    @SerializedName("varue")
    var varue: String? = null
) : RealmObject()