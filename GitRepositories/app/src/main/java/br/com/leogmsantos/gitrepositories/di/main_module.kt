package br.com.leogmsantos.gitrepositories.di

import br.com.leogmsantos.gitrepositories.view.ui.home.MainViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel {
        MainViewModel(
            get(),
            get()
        )
    }
}