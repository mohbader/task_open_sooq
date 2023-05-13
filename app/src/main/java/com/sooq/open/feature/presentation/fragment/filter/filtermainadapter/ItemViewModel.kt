package com.sooq.open.feature.presentation.fragment.filter.filtermainadapter

import com.sooq.open.feature.presentation.fragment.filter.filtermainadapter.ViewHolderTypeFactory

interface ItemViewModel {
    fun type(viewHolderTypeFactory: ViewHolderTypeFactory): Int
}