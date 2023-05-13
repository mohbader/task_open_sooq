package com.sooq.open.feature.presentation.fragment.category

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.viewModels
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.sooq.open.databinding.FragmentCategoryBinding
import com.sooq.open.feature.domain.model.CategoryModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class CategoryFragment : Fragment() {

    private lateinit var _binding: FragmentCategoryBinding
    private val viewModel: CategoryViewModel by viewModels()
    private val categoryAdapter: CategoryAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentCategoryBinding.inflate(inflater, container, false)
        return _binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initObservables()
        initListener()
    }

    private fun initListener() {
        _binding.editTextSearch.doOnTextChanged { text, start, before, count ->
            categoryAdapter?.filter?.filter(text)
        }
    }

    private fun initObservables() {
        lifecycleScope.launch {
            viewModel.categoriesStateFlow.flowWithLifecycle(lifecycle).collectLatest {
                it?.let {
                    handelSuccessData(it)
                }
            }
        }
    }

    private fun handelSuccessData(categories: List<CategoryModel>) {
        val categoryAdapter = CategoryAdapter() {
            val action = CategoryFragmentDirections.actionCategoryFragmentToSubCategoryFragment(it)
            findNavController().navigate(action)
        }
        _binding.recycleViewCategory.apply {
            adapter = categoryAdapter
        }
        categoryAdapter?.submitList(categories)
    }

}