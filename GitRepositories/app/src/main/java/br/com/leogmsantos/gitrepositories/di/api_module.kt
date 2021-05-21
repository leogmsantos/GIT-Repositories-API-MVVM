package br.com.leogmsantos.gitrepositories.di

import br.com.leogmsantos.gitrepositories.services.APIServices
import org.koin.dsl.module
import retrofit2.Retrofit

val apiModule = module {
    single(createdAtStart = false) {
        get<Retrofit>().create(APIServices::class.java)
    }
}