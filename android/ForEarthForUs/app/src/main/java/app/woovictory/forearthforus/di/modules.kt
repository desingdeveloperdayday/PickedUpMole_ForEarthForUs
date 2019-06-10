package app.woovictory.forearthforus.di

import app.woovictory.forearthforus.BuildConfig
import app.woovictory.forearthforus.api.ApiService
import app.woovictory.forearthforus.data.repository.EarthRepository
import app.woovictory.forearthforus.data.repository.account.LoginRepository
import app.woovictory.forearthforus.data.repository.account.PreferenceRepository
import app.woovictory.forearthforus.data.repository.account.SignUpRepository
import app.woovictory.forearthforus.data.repository.category.MissionCategoryRepository
import app.woovictory.forearthforus.data.repository.feed.MissionFeedRepository
import app.woovictory.forearthforus.data.source.account.LoginRemoteDataSource
import app.woovictory.forearthforus.data.source.account.PreferenceRemoteDataSource
import app.woovictory.forearthforus.data.source.account.SignUpRemoteDataSource
import app.woovictory.forearthforus.data.source.category.MissionCategoryRemoteDataSource
import app.woovictory.forearthforus.data.source.feed.MissionFeedRemoteDataSource
import app.woovictory.forearthforus.data.source.main.EarthRemoteDataSource
import app.woovictory.forearthforus.util.baseURL
import app.woovictory.forearthforus.util.headerInterceptor
import app.woovictory.forearthforus.util.loggingInterceptor
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module.module
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
                    .addInterceptor(get(headerInterceptor))
                    .addInterceptor(get(loggingInterceptor))
                    .build()
            )
            .baseUrl(baseURL)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiService::class.java)
    }

    single(headerInterceptor) {
        Interceptor { chain ->
            val builder = chain.request().newBuilder().apply {
                header("Content-Type", "application/json")
            }
            chain.proceed(builder.build())
        }
    }

    single(loggingInterceptor) {
        HttpLoggingInterceptor().apply {
            level = if (BuildConfig.DEBUG)
                HttpLoggingInterceptor.Level.BODY
            else
                HttpLoggingInterceptor.Level.NONE
        } as Interceptor
    }
}

val dataSourceModule = module {
    factory { LoginRemoteDataSource(get()) }
    factory { SignUpRemoteDataSource(get()) }
    factory { EarthRemoteDataSource(get()) }
    factory { MissionFeedRemoteDataSource(get()) }
    factory { PreferenceRemoteDataSource(get()) }
    factory { MissionCategoryRemoteDataSource(get()) }

}

val repositoryModule = module {
    factory { SignUpRepository(get()) }
    factory { LoginRepository(get()) }
    factory { EarthRepository(get()) }
    factory { MissionFeedRepository(get()) }
    factory { PreferenceRepository(get()) }
    factory { MissionCategoryRepository(get()) }
}

var appModules = listOf(apiModule, dataSourceModule, viewModelModule, repositoryModule)