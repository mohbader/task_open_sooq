package com.sooq.open.feature.data.localdata.dynamic.assign


import com.google.gson.annotations.SerializedName
import io.realm.RealmObject

open class DynamicAssign(
    @SerializedName("data")
    var data: Data? = null
):RealmObject()