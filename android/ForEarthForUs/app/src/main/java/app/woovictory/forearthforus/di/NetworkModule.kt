package app.woovictory.forearthforus.di

import app.woovictory.forearthforus.BuildConfig
import app.woovictory.forearthforus.api.ApiService
import app.woovictory.forearthforus.data.repository.account.LoginRepository
import app.woovictory.forearthforus.data.repository.account.PreferenceRepository
import app.woovictory.forearthforus.data.repository.account.SignUpRepository
import app.woovictory.forearthforus.data.repository.article.ArticleDetailRepository
import app.woovictory.forearthforus.data.repository.article.ArticleDonationRepository
import app.woovictory.forearthforus.data.repository.article.ArticleRepository
import app.woovictory.forearthforus.data.repository.category.MissionCategoryRepository
import app.woovictory.forearthforus.data.repository.feed.MissionFeedRepository
import app.woovictory.forearthforus.data.repository.main.EarthRepository
import app.woovictory.forearthforus.data.repository.mission.MissionDetailRepository
import app.woovictory.forearthforus.data.repository.mission.MissionFeedCompleteRepository
import app.woovictory.forearthforus.data.repository.mission.MissionSelectListRepository
import app.woovictory.forearthforus.data.repository.mission.MissionSelectRepository
import app.woovictory.forearthforus.data.repository.scrap.ScrapRepository
import app.woovictory.forearthforus.data.source.account.LoginRemoteDataSource
import app.woovictory.forearthforus.data.source.account.PreferenceRemoteDataSource
import app.woovictory.forearthforus.data.source.account.SignUpRemoteDataSource
import app.woovictory.forearthforus.data.source.article.ArticleDetailRemoteDataSource
import app.woovictory.forearthforus.data.source.article.ArticleDonationRemoteDataSource
import app.woovictory.forearthforus.data.source.article.ArticleRemoteDataSource
import app.woovictory.forearthforus.data.source.category.MissionCategoryRemoteDataSource
import app.woovictory.forearthforus.data.source.feed.MissionFeedRemoteDataSource
import app.woovictory.forearthforus.data.source.main.EarthRemoteDataSource
import app.woovictory.forearthforus.data.source.mission.MissionDetailRemoteDataSource
import app.woovictory.forearthforus.data.source.mission.MissionFeedCompleteRemoteDataSource
import app.woovictory.forearthforus.data.source.mission.MissionSelectListRemoteDataSource
import app.woovictory.forearthforus.data.source.mission.MissionSelectRemoteDataSource
import app.woovictory.forearthforus.data.source.scrap.ScrapRemoteDataSource
import app.woovictory.forearthforus.utils.headerInterceptor
import app.woovictory.forearthforus.utils.loggingInterceptor
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.core.qualifier.named
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Created by VictoryWoo
 * TODO
 * 추후에 token 바로 header 에 지정할 수 있도록 custom 하게 만들어야 함.
 */


val apiModule = module {
    single<ApiService> {
        Retrofit.Builder()
            .client(
                OkHttpClient.Builder()
                    .addInterceptor(get(named(headerInterceptor)))
                    .addInterceptor(get(named(loggingInterceptor)))
                    .build()
            )
            .baseUrl(BuildConfig.BASE_URL)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.createAsync())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiService::class.java)
    }

    single(named(headerInterceptor)) {
        Interceptor { chain ->
            val builder = chain.request().newBuilder().apply {
                header("Content-Type", "application/json")
            }
            chain.proceed(builder.build())
        }
    }

    single(named(loggingInterceptor)) {
        HttpLoggingInterceptor().apply {
            level = if (BuildConfig.DEBUG)
                HttpLoggingInterceptor.Level.BODY
            else
                HttpLoggingInterceptor.Level.NONE
        } as Interceptor
    }
}




