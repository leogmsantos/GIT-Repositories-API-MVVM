package br.com.leogmsantos.gitrepositories.view.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import br.com.leogmsantos.gitrepositories.model.GITRepository
import br.com.leogmsantos.gitrepositories.persistence.GITRepositoryDao
import br.com.leogmsantos.gitrepositories.services.APIServices
import br.com.leogmsantos.gitrepositories.services.response.GITRepositoryResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainViewModel(private val api: APIServices, private val repository: GITRepositoryDao) : ViewModel() {
    
    private val _repositories = MutableLiveData<List<GITRepository>>()
    val repositories: LiveData<List<GITRepository>> get() = _repositories

    private val _repositoriesError = MutableLiveData<String?>()
    val repositoriesError: LiveData<String?> get() = _repositoriesError

    suspend fun serchRepositories(name: String, page: Int, dynamicSearch:Boolean) = withContext(Dispatchers.Main){
        val repositories = repository.getAllRepositories()
        if (repositories.isEmpty() || page != 1 || dynamicSearch){
            val call: Call<GITRepositoryResponse> = api.getGitRepositoryList(searchedLanguage = name, page = page)
            call.enqueue(object : Callback<GITRepositoryResponse>{
                override fun onFailure(call: Call<GITRepositoryResponse>, t: Throwable) {
                    _repositoriesError.value = t.message
                }
                override fun onResponse(
                    call: Call<GITRepositoryResponse>,
                    response: Response<GITRepositoryResponse>
                ) {
                    if (response.isSuccessful){
                        response.body()?.repositories.let {
                            _repositories.value = it
                            if (it != null) {
                                repository.insertRepositories(it)
                            }
                        }
                    }
                }
            })
        }else{
           _repositories.value = repositories
        }
    }
}