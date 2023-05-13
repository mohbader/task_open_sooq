package com.sooq.open.feature.data.localdata.dynamic.Option


import com.google.gson.annotations.SerializedName
import io.realm.RealmObject

open class DynamicOption(
    @SerializedName("data")
    var data: DynamicOptionData? = null
):RealmObject()