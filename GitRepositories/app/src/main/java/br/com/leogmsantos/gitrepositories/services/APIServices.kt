package br.com.leogmsantos.gitrepositories.services

import br.com.leogmsantos.gitrepositories.services.response.GITRepositoryResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface APIServices {

    @GET("repositories")
    fun getGitRepositoryList(
        @Query("q") searchedLanguage: String? = "android",
        @Query("per_page") limit: Int? = 10,
        @Query("page") page: Int? = 1
    ): Call<GITRepositoryResponse>

}