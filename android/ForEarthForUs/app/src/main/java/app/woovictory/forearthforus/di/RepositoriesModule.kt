package app.woovictory.forearthforus.di

import app.woovictory.forearthforus.data.repository.account.LoginRepository
import app.woovictory.forearthforus.data.repository.account.PreferenceRepository
import app.woovictory.forearthforus.data.repository.account.SignUpRepository
import app.woovictory.forearthforus.data.repository.article.ArticleDetailRepository
import app.woovictory.forearthforus.data.repository.article.ArticleDonationRepository
import app.woovictory.forearthforus.data.repository.category.MissionCategoryRepository
import app.woovictory.forearthforus.data.repository.feed.MissionFeedRepository
import app.woovictory.forearthforus.data.repository.main.EarthRepository
import app.woovictory.forearthforus.data.repository.mission.MissionDetailRepository
import app.woovictory.forearthforus.data.repository.mission.MissionFeedCompleteRepository
import app.woovictory.forearthforus.data.repository.mission.MissionSelectListRepository
import app.woovictory.forearthforus.data.repository.mission.MissionSelectRepository
import app.woovictory.forearthforus.data.repository.scrap.ScrapRepository
import app.woovictory.forearthforus.data.source.article.ArticleRemoteDataSource
import org.koin.dsl.module

/**
 * Created by VictoryWoo
 */

val repositoryModule = module {
    single { SignUpRepository(get()) }
    single { LoginRepository(get()) }
    single { EarthRepository(get()) }
    single { MissionFeedRepository(get()) }
    single { PreferenceRepository(get()) }
    single { MissionCategoryRepository(get()) }
    single { MissionSelectListRepository(get()) }
    single { MissionDetailRepository(get()) }
    single { MissionSelectRepository(get()) }
    single { ArticleDonationRepository(get()) }
    single { ArticleDetailRepository(get()) }
    single { ArticleRemoteDataSource(get()) }
    single { MissionFeedCompleteRepository(get()) }
    single { ScrapRepository(get()) }
}