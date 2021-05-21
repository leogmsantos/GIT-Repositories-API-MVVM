package br.com.leogmsantos.gitrepositories.persistence

import androidx.room.TypeConverter
import br.com.leogmsantos.gitrepositories.model.GITRepositoryOwner
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class Converter {

    @TypeConverter
    fun fromString(value: String): GITRepositoryOwner {
        val listType = object : TypeToken<GITRepositoryOwner>() {}.type
        return Gson().fromJson<GITRepositoryOwner>(value, listType)
    }

    @TypeConverter
    fun fromObject(owner: GITRepositoryOwner): String {
        val gson = Gson()
        return gson.toJson(owner)
    }
}