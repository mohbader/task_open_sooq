package com.sooq.open.feature.data.localdata.dynamic

import com.sooq.open.feature.data.localdata.dynamic.Option.Field
import com.sooq.open.feature.data.localdata.dynamic.Option.Option
import com.sooq.open.feature.data.localdata.dynamic.assign.FieldsLabel

data class FilterData(
    var field: Field? = null,
    var fieldLabel: FieldsLabel? = null,
    var option: List<Option>? = null
)