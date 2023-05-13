package com.sooq.open.feature.presentation.fragment.filter.filtermainadapter

import android.content.ClipData
import android.view.View
import com.sooq.open.feature.data.localdata.dynamic.FilterData
import com.sooq.open.feature.presentation.fragment.filter.filtermainadapter.viewhplders.BooleanViewHolder

class ViewHolderTypeFactoryImp:ViewHolderTypeFactory {
    override fun create(parent: View, viewType: Int): BaseViewHolder<FilterData> {

        return BooleanViewHolder(parent)
    }

    override fun type(item: FilterData): Int {
        return 1
    }
}