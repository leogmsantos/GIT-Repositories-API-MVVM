package br.com.leogmsantos.gitrepositories.model

import br.com.leogmsantos.gitrepositories.base.DTO
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class GITRepositoryOwner (
    @SerializedName("avatar_url")
    val avatarUrl: String,
    val url: String,
    val login: String
): DTO