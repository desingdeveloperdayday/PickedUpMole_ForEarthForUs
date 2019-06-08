package app.woovictory.forearthforus.di

import app.woovictory.forearthforus.vm.account.LoginViewModel
import app.woovictory.forearthforus.vm.MainViewModel
import app.woovictory.forearthforus.vm.account.FieldSelectViewModel
import app.woovictory.forearthforus.vm.account.SignUpViewModel
import app.woovictory.forearthforus.vm.category.MissionCategoryViewModel
import org.koin.androidx.viewmodel.ext.koin.viewModel
import org.koin.dsl.module.module

/**
 * Created by VictoryWoo
 */

val viewModelModule = module {
    viewModel { SignUpViewModel(get()) }
    viewModel { LoginViewModel(get()) }
    viewModel { MainViewModel(get(), get()) }
    viewModel { FieldSelectViewModel(get()) }
    viewModel { MissionCategoryViewModel(get()) }
}