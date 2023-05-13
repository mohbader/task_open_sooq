package com.sooq.open.feature.presentation.fragment.filter.filtermainadapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.sooq.open.feature.data.localdata.dynamic.FilterData

class FilterMainAdapter : RecyclerView.Adapter<BaseViewHolder<FilterData>>() {

    private val typeFactory: ViewHolderTypeFactory = ViewHolderTypeFactoryImp()

    private val items = mutableListOf<FilterData>()

    fun setList(list: List<FilterData>) {
        items.clear()
        items.addAll(list)
        notifyDataSetChanged()
    }

    override fun getItemViewType(position: Int): Int {
        return 1
//        return items[position].type(typeFactory)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<FilterData> {
        return typeFactory.create(
            LayoutInflater
                .from(parent.context)
                .inflate(viewType, parent, false), viewType
        )
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: BaseViewHolder<FilterData>, position: Int) {
        holder.bind(items[position])
    }
}