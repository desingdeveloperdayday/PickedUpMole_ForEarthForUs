package app.woovictory.forearthforus.di

import app.woovictory.forearthforus.data.repository.article.ArticleRepository
import app.woovictory.forearthforus.data.source.account.LoginRemoteDataSource
import app.woovictory.forearthforus.data.source.account.PreferenceRemoteDataSource
import app.woovictory.forearthforus.data.source.account.SignUpRemoteDataSource
import app.woovictory.forearthforus.data.source.article.ArticleDetailRemoteDataSource
import app.woovictory.forearthforus.data.source.article.ArticleDonationRemoteDataSource
import app.woovictory.forearthforus.data.source.category.MissionCategoryRemoteDataSource
import app.woovictory.forearthforus.data.source.feed.MissionFeedRemoteDataSource
import app.woovictory.forearthforus.data.source.main.EarthRemoteDataSource
import app.woovictory.forearthforus.data.source.mission.MissionDetailRemoteDataSource
import app.woovictory.forearthforus.data.source.mission.MissionFeedCompleteRemoteDataSource
import app.woovictory.forearthforus.data.source.mission.MissionSelectListRemoteDataSource
import app.woovictory.forearthforus.data.source.mission.MissionSelectRemoteDataSource
import app.woovictory.forearthforus.data.source.scrap.ScrapRemoteDataSource
import org.koin.dsl.module

/**
 * Created by VictoryWoo
 */

val dataSourceModule = module {
    single { LoginRemoteDataSource(get()) }
    single { SignUpRemoteDataSource(get()) }
    single { EarthRemoteDataSource(get()) }
    single { MissionFeedRemoteDataSource(get()) }
    single { PreferenceRemoteDataSource(get()) }
    single { MissionCategoryRemoteDataSource(get()) }
    single { MissionSelectListRemoteDataSource(get()) }
    single { MissionDetailRemoteDataSource(get()) }
    single { MissionSelectRemoteDataSource(get()) }
    single { ArticleDonationRemoteDataSource(get()) }
    single { ArticleDetailRemoteDataSource(get()) }
    single { ArticleRepository(get()) }
    single { MissionFeedCompleteRemoteDataSource(get()) }
    single { ScrapRemoteDataSource(get()) }
}