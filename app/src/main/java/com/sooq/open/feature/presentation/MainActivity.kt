package com.sooq.open.feature.presentation

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupActionBarWithNavController
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.sooq.open.R
import com.sooq.open.databinding.ActivityMainBinding
import com.sooq.open.feature.data.localdata.CategoryDataLocal
import com.sooq.open.feature.data.localdata.dynamic.Option.DynamicOption
import com.sooq.open.feature.data.localdata.dynamic.assign.DynamicAssign
import dagger.hilt.android.AndroidEntryPoint
import java.io.IOException

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {


    private lateinit var _binding: ActivityMainBinding
    private val viewModel: MainViewModel by viewModels()

    private val navHostFragment by lazy {
        (supportFragmentManager.findFragmentById(R.id.activity_main_nav_host_fragment) as NavHostFragment)
    }
    private val navController by lazy { navHostFragment.navController }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        _binding = ActivityMainBinding.inflate(layoutInflater)
        val view = _binding.root
        setContentView(view)

        setupActionBarWithNavController(navController)

        getCategories(context = this)
        getAssign(context = this)
        getOption(context = this)
    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp() || super.onSupportNavigateUp()
    }

    private fun getCategories(context: Context) {

        lateinit var jsonString: String
        try {
            jsonString = context.assets.open("categories_and_sub_categories.json")
                .bufferedReader()
                .use { it.readText() }
        } catch (ioException: IOException) {
            Log.d("parsing ss", ioException.message.orEmpty())
        }

        val categories = object : TypeToken<CategoryDataLocal>() {}.type
        viewModel.insertLocalData(Gson().fromJson(jsonString, categories))
    }

    private fun getOption(context: Context) {
        lateinit var jsonString: String
        try {
            jsonString = context.assets.open("dynamic_options.json")
                .bufferedReader()
                .use { it.readText() }
        } catch (ioException: IOException) {
            Log.d("parsing ss", ioException.message.orEmpty())
        }

        val options = object : TypeToken<DynamicOption>() {}.type
        if (options != null)
            viewModel.insertOption(Gson().fromJson(jsonString, options))
    }

    private fun getAssign(context: Context) {
        lateinit var jsonString: String
        try {
            jsonString = context.assets.open("dynamic_assgin.json")
                .bufferedReader()
                .use { it.readText() }
        } catch (ioException: IOException) {
            Log.d("parsing ss", ioException.message.orEmpty())
        }

        val assign = object : TypeToken<DynamicAssign>() {}.type
        if (assign != null)
            viewModel.insertAssign(Gson().fromJson(jsonString, assign))
    }

    override fun onBackPressed() {
        if (navController.navigateUp() || super.onSupportNavigateUp())
            navController.popBackStack()
        else
            finish()
    }
}