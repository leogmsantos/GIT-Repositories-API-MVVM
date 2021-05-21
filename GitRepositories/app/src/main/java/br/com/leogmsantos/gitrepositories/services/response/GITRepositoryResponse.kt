package br.com.leogmsantos.gitrepositories.services.response

import br.com.leogmsantos.gitrepositories.model.GITRepository
import br.com.leogmsantos.gitrepositories.base.DTO
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class GITRepositoryResponse (
    @SerializedName("items")
    var repositories: List<GITRepository> = listOf()
) : DTO