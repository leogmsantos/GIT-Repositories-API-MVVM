package br.com.leogmsantos.gitrepositories.view.ui.home

import android.os.Bundle
import android.view.Menu
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.core.widget.NestedScrollView
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import br.com.leogmsantos.gitrepositories.R
import br.com.leogmsantos.gitrepositories.databinding.HomeBinding


import br.com.leogmsantos.gitrepositories.model.GITRepository
import br.com.leogmsantos.gitrepositories.view.adapter.RepositoryAdapter
import br.com.leogmsantos.gitrepositories.util.LoadingDialog
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    private lateinit var binding: HomeBinding
    private val mainViewModel: MainViewModel by viewModel()
    private var repositories:ArrayList<GITRepository> = arrayListOf()
    private val adapter =
        RepositoryAdapter(
            this,
            repositories
        )
    private var page = 1
    private var submit: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.home)

        binding.apply {
            vm = mainViewModel
            lifecycleOwner = this@MainActivity

            nsv.setOnScrollChangeListener(NestedScrollView.OnScrollChangeListener { v, _, scrollY, _, _ ->
                if (v != null) {
                    if (scrollY == v.getChildAt(0).measuredHeight - v.measuredHeight){
                        page++
                        LoadingDialog.loading(this@MainActivity)
                        if (submit.isNullOrEmpty()) callSearchRepositories(page = page) else callSearchRepositories(submit!!, page)
                    }
                }
            })

            rvRepositoryList.adapter = adapter
            rvRepositoryList.layoutManager = LinearLayoutManager(this@MainActivity)
        }

        LoadingDialog.loading(this)
        callSearchRepositories()

        mainViewModel.repositories.observe(this, Observer {
            repositories = it as ArrayList<GITRepository>
            adapter.updateRepositories(repositories)
            LoadingDialog.dismissLoading()
        })

        mainViewModel.repositoriesError.observe(this, Observer {
            LoadingDialog.dismissLoading()
            Toast.makeText(this, it, Toast.LENGTH_LONG).show()
        })
    }

    fun callSearchRepositories(name: String = getString(R.string.default_filter), page: Int = 1, dynamicSearch:Boolean = false){
        GlobalScope.launch {
            mainViewModel.serchRepositories(name, page, dynamicSearch)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.toolbar_menu, menu)
        val item = menu.findItem(R.id.m_search)
        val searchView =
            item.actionView as SearchView
        searchView.setOnQueryTextListener(object :
            SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                return false
            }
            override fun onQueryTextChange(newText: String): Boolean {
                adapter.clearList()
                if (newText.isEmpty()){
                    submit = null
                    callSearchRepositories()
                }else{
                    submit = newText
                    callSearchRepositories(name = newText, dynamicSearch = true)
                }
                return false
            }
        })
        return super.onCreateOptionsMenu(menu)
    }
}