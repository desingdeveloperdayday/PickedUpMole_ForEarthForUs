package app.woovictory.forearthforus.di

import app.woovictory.forearthforus.vm.account.LoginViewModel
import app.woovictory.forearthforus.vm.main.MainViewModel
import app.woovictory.forearthforus.vm.account.FieldSelectViewModel
import app.woovictory.forearthforus.vm.account.SignUpViewModel
import app.woovictory.forearthforus.vm.article.ArticleDetailViewModel
import app.woovictory.forearthforus.vm.article.ArticleViewModel
import app.woovictory.forearthforus.vm.category.MissionCategoryViewModel
import app.woovictory.forearthforus.vm.mission.MissionFeedCompleteWriteViewModel
import app.woovictory.forearthforus.vm.mission.MissionDetailViewModel
import app.woovictory.forearthforus.vm.mission.MissionSelectViewModel
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
    viewModel { MissionSelectViewModel(get()) }
    viewModel { MissionDetailViewModel(get(), get()) }
    viewModel { ArticleViewModel(get(),get()) }
    viewModel { ArticleDetailViewModel(get()) }
    viewModel { MissionFeedCompleteWriteViewModel(get()) }
}