package app.woovictory.forearthforus.di

import app.woovictory.forearthforus.BuildConfig
import app.woovictory.forearthforus.api.ApiService
import app.woovictory.forearthforus.data.repository.LoginRepository
import app.woovictory.forearthforus.data.repository.SignUpRepository
import app.woovictory.forearthforus.data.source.LoginRemoteDataSource
import app.woovictory.forearthforus.data.source.SignUpRemoteDataSource
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

val sourceModule = module {
    factory { LoginRemoteDataSource(get()) }
    factory { SignUpRemoteDataSource(get()) }

}

val repositoryModule = module {
    factory { SignUpRepository(get()) }
    factory { LoginRepository(get()) }
}

var appModules = listOf(apiModule, sourceModule, viewModelModule, repositoryModule)