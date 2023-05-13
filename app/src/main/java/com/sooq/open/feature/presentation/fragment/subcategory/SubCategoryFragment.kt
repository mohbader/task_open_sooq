package com.sooq.open.feature.presentation.fragment.subcategory

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.doAfterTextChanged
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.viewModels
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.sooq.open.R
import com.sooq.open.databinding.FragmentCategoryBinding
import com.sooq.open.databinding.FragmentSubCategoryBinding
import com.sooq.open.feature.data.localdata.SubCategoryLocalModel
import com.sooq.open.feature.domain.model.CategoryModel
import com.sooq.open.feature.domain.model.SubCategoryModel
import com.sooq.open.feature.presentation.fragment.category.CategoryAdapter
import com.sooq.open.feature.presentation.fragment.category.CategoryFragmentDirections
import com.sooq.open.feature.presentation.fragment.category.CategoryViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch


@AndroidEntryPoint
class SubCategoryFragment : Fragment() {

    private val args by navArgs<SubCategoryFragmentArgs>()
    private lateinit var _binding: FragmentSubCategoryBinding
    private val viewModel: SubCategoryViewModel by viewModels()
    private var subCategoryAdapter: SubCategoryAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSubCategoryBinding.inflate(inflater, container, false)
        return _binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getSubCategories()
        initObservables()
        initListener()
    }

    private fun initListener() {
        _binding.editTextSearch.doOnTextChanged { text, start, before, count ->
            subCategoryAdapter?.filter?.filter(text)
        }
    }

    private fun initObservables() {
        lifecycleScope.launch {
            viewModel.subCategoriesStateFlow.flowWithLifecycle(lifecycle).collectLatest {
                it?.let {
                    handelSuccessData(it)
                }
            }
        }
    }

    private fun getSubCategories() {
        args?.id?.let { viewModel.getSubCategory(it) }
    }

    private fun handelSuccessData(categories: List<SubCategoryModel>) {
        subCategoryAdapter = SubCategoryAdapter() {
            val action = SubCategoryFragmentDirections.actionSubCategoryFragmentToFilterFragment(it)
            findNavController().navigate(action)
        }
        _binding.recycleViewSubCategory.apply {
            adapter = subCategoryAdapter
        }
        subCategoryAdapter?.submitList(categories)
    }

}