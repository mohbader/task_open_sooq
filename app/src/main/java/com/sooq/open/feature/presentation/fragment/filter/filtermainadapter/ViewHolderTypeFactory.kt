package com.sooq.open.feature.presentation.fragment.filter.filtermainadapter

import android.view.View
import com.sooq.open.feature.data.localdata.dynamic.FilterData

interface ViewHolderTypeFactory {
    fun type(item: FilterData): Int
    fun create(parent: View, viewType: Int): BaseViewHolder<FilterData>
}