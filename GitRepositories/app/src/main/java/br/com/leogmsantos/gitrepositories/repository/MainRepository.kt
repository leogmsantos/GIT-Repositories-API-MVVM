package br.com.leogmsantos.gitrepositories.repository

import androidx.lifecycle.MutableLiveData
import br.com.leogmsantos.gitrepositories.model.GITRepository
import br.com.leogmsantos.gitrepositories.persistence.GITRepositoryDao
import br.com.leogmsantos.gitrepositories.services.APIServices
import br.com.leogmsantos.gitrepositories.services.response.GITRepositoryResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainRepository constructor(
    private val api: APIServices,
    private val repository: GITRepositoryDao
) {
    suspend fun serchRepositories(name: String = "android", page: Int = 1, dynamicSearch:Boolean = false) = withContext(
        Dispatchers.IO){
        val liveData = MutableLiveData<List<GITRepository>>()
        val repositories = repository.getAllRepositories()
        if (repositories.isEmpty() || page != 1 || dynamicSearch){
            val call: Call<GITRepositoryResponse> = api.getGitRepositoryList(searchedLanguage = name, page = page)
            call.enqueue(object : Callback<GITRepositoryResponse> {
                override fun onFailure(call: Call<GITRepositoryResponse>, t: Throwable) {

                }
                override fun onResponse(
                    call: Call<GITRepositoryResponse>,
                    response: Response<GITRepositoryResponse>
                ) {
                    if (response.isSuccessful){
                        response.body()?.repositories.let {
                            liveData.postValue(it)
                            if (it != null) {
                                repository.insertRepositories(it)
                            }
                        }
                    }
                }
            })
        }else{
            liveData.apply { postValue(repositories) }
        }
    }
}