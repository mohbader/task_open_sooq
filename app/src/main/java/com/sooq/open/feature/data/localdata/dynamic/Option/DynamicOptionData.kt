package com.sooq.open.feature.data.localdata.dynamic.Option


import com.google.gson.annotations.SerializedName
import io.realm.RealmList
import io.realm.RealmObject

open class DynamicOptionData(

    @SerializedName("fields")
    var fields: RealmList<Field>? = null,

    @SerializedName("options")
    var options: RealmList<Option>? = null

):RealmObject()