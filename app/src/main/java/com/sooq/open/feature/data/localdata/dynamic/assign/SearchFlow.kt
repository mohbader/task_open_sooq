package com.sooq.open.feature.data.localdata.dynamic.assign


import com.google.gson.annotations.SerializedName
import io.realm.RealmList
import io.realm.RealmObject

open  class SearchFlow(
    @SerializedName("category_id")
    var categoryId: Int? = null,
    @SerializedName("order")
    var order: RealmList<String>? = null
):RealmObject()