package app.woovictory.forearthforus.di

import app.woovictory.forearthforus.vm.LoginViewModel
import app.woovictory.forearthforus.vm.MainViewModel
import app.woovictory.forearthforus.vm.SignUpViewModel
import org.koin.androidx.viewmodel.ext.koin.viewModel
import org.koin.dsl.module.module

/**
 * Created by VictoryWoo
 */

val viewModelModule = module {
    viewModel { SignUpViewModel(get()) }
    viewModel { LoginViewModel(get()) }
    viewModel { MainViewModel(get()) }
}