package br.com.leogmsantos.gitrepositories.model

import androidx.room.*
import br.com.leogmsantos.gitrepositories.base.DTO
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Entity
@Parcelize
data class GITRepository (
    @PrimaryKey(autoGenerate = true)
    val entityId: Long,
    val id: Long,
    val name: String,
    @ColumnInfo(defaultValue = "")
    val description: String,
    val owner: GITRepositoryOwner,
    val forks: Int,
    val watchers: Int,
    @SerializedName("clone_url")
    val cloneUrl: String,
    @ColumnInfo(defaultValue = "")
    val language: String,
    @SerializedName("created_at")
    @ColumnInfo(defaultValue = "")
    val createdAt: String,
    @SerializedName("updated_at")
    @ColumnInfo(defaultValue = "")
    val lastUpdate: String
): DTO