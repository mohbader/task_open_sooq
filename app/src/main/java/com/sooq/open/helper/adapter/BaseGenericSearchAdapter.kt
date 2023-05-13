package com.sooq.open.helper.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import java.lang.reflect.ParameterizedType

abstract class BaseGenericSearchAdapter<VB : ViewDataBinding, MODEL : BaseSearchModel>(
    private var dataList: MutableList<MODEL> = mutableListOf(),
    private val defaultItemViewClick: Boolean = true,
) : ListAdapter<MODEL, BaseGenericSearchAdapter<VB, MODEL>.Holder?>(DiffCallback()), Filterable {

    var onClickItem: (MODEL) -> Unit = {}

    private val filterList: MutableList<MODEL> = mutableListOf()

    fun <T : BaseGenericSearchAdapter<VB, MODEL>> setList(dataList: List<MODEL>): T {
        this.dataList = dataList.toMutableList()
        return this as T
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val type = javaClass.genericSuperclass
        val clazz = (type as ParameterizedType).actualTypeArguments[0] as Class<VB>

        val method = clazz.getMethod(
            "inflate",
            LayoutInflater::class.java,
            ViewGroup::class.java,
            Boolean::class.java
        )

        return Holder(
            method.invoke(null, LayoutInflater.from(parent.context), parent, false) as VB
        )
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        if (dataList.isEmpty()) {
            dataList.addAll(currentList)
        }
        setupViewHolder(holder, position, getItem(position))
    }


    abstract fun setupViewHolder(holder: Holder, position: Int, item: MODEL)

    inner class Holder(binding: VB) : BaseListAdapterViewHolder<VB, Any?>(binding)


    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(charSequence: CharSequence?): FilterResults {
                val filterResult = FilterResults()
                if (charSequence?.isNullOrEmpty()?.not() == true) {
                    filterResult.values = dataList.filter { item ->
                        item.getSearchCriteria().contains(charSequence.trim(), true)
                    }
                } else {
                    filterResult.values = dataList
                }
                return filterResult
            }

            @SuppressLint("NotifyDataSetChanged")
            override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
                filterList.clear()
                filterList.addAll((results?.values) as List<MODEL>)
                submitList(filterList)
                notifyDataSetChanged()
            }
        }
    }


    class DiffCallback<MODEL : BaseSearchModel> : DiffUtil.ItemCallback<MODEL>() {
        override fun areItemsTheSame(
            oldItem: MODEL,
            newItem: MODEL
        ): Boolean {
            return oldItem == newItem
        }

        @SuppressLint("DiffUtilEquals")
        override fun areContentsTheSame(
            oldItem: MODEL,
            newItem: MODEL
        ): Boolean {
            return oldItem == newItem
        }
    }
}

open class BaseListAdapterViewHolder<T : ViewDataBinding, U>(val binding: T) :
    RecyclerView.ViewHolder(binding.root) {
    fun bind(idVar: Int, item: Any?) {
        binding.setVariable(idVar, item)
        binding.executePendingBindings()
    }

}
