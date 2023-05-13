package com.sooq.open.feature.data.localdata.dynamic.assign


import com.google.gson.annotations.SerializedName
import io.realm.RealmList
import io.realm.RealmObject

open class Data(
    @SerializedName("fields_labels")
    var fieldsLabels: RealmList<FieldsLabel>? = null,
    @SerializedName("search_flow")
    var searchFlow: RealmList<SearchFlow>? = null
):RealmObject()