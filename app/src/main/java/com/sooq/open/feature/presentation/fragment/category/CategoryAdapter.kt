package com.sooq.open.feature.presentation.fragment.category

import com.sooq.open.BR
import com.sooq.open.databinding.RowCategortyItemBinding
import com.sooq.open.feature.domain.model.CategoryModel
import com.sooq.open.helper.adapter.BaseGenericSearchAdapter

class CategoryAdapter(
    private val onIteClicked: (id: Int) -> Unit
) : BaseGenericSearchAdapter<RowCategortyItemBinding, CategoryModel>() {
    override fun setupViewHolder(holder: Holder, position: Int, item: CategoryModel) {
        holder.bind(BR.category, item)
        holder.binding.category = item

        holder.binding.root.setOnClickListener {
            item.id?.let { it1 -> onIteClicked(it1) }
        }
    }
}