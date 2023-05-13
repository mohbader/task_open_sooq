package com.sooq.open.feature.presentation.fragment.filter

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import com.sooq.open.R
import com.sooq.open.databinding.FragmentFilterBinding
import com.sooq.open.feature.presentation.fragment.subcategory.SubCategoryFragmentArgs
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class FilterFragment : Fragment() {

    private lateinit var _binding: FragmentFilterBinding
    private val args by navArgs<FilterFragmentArgs>()
    private val viewModel: FilterViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentFilterBinding.inflate(inflater, container, false)
        return _binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initObservables()
        getFilterData()
    }

    private fun getFilterData() {
        args?.id?.let {
            viewModel.getFilterData(it)
        }
    }

    private fun initObservables() {
        lifecycleScope.launch {
            viewModel.filterDataStateFlow.flowWithLifecycle(lifecycle).collectLatest {
                it?.let {
                    Toast.makeText(context, it.size.toString(), Toast.LENGTH_LONG).show()
                }
            }
        }
    }

}