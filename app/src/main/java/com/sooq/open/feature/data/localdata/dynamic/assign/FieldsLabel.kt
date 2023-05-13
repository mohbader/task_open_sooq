package com.sooq.open.feature.data.localdata.dynamic.assign


import com.google.gson.annotations.SerializedName
import io.realm.RealmObject

open class FieldsLabel(
    @SerializedName("field_name")
    var fieldName: String? = null,
    @SerializedName("label_ar")
    var labelAr: String? = null,
    @SerializedName("label_en")
    var labelEn: String? = null
):RealmObject()