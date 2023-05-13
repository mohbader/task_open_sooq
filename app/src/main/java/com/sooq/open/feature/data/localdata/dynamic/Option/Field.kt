package com.sooq.open.feature.data.localdata.dynamic.Option


import com.google.gson.annotations.SerializedName
import io.realm.RealmObject

open class Field(
    @SerializedName("data_type")
    var dataType: String? = null,
    @SerializedName("id")
    var id: Int? = null,
    @SerializedName("name")
    var name: String? = null,
    @SerializedName("parent_id")
    var parentId: Int? = null,
    @SerializedName("parent_name")
    var parentName: String? = null
):RealmObject()