package com.sooq.open.feature.presentation.fragment.subcategory

import com.sooq.open.BR
import com.sooq.open.databinding.RowCategortyItemBinding
import com.sooq.open.databinding.RowSubCategortyItemBinding
import com.sooq.open.feature.domain.model.CategoryModel
import com.sooq.open.feature.domain.model.SubCategoryModel
import com.sooq.open.helper.adapter.BaseGenericSearchAdapter

class SubCategoryAdapter(
    private val onIteClicked: (id: Int) -> Unit
) : BaseGenericSearchAdapter<RowSubCategortyItemBinding, SubCategoryModel>() {
    override fun setupViewHolder(holder: Holder, position: Int, item: SubCategoryModel) {
        holder.bind(BR.subCategory, item)
        holder.binding.subCategory = item

        holder.binding.root.setOnClickListener {
            item.id?.let { it1 -> onIteClicked(it1) }
        }
    }
}