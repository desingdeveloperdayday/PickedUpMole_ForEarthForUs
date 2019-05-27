package app.woovictory.forearthforus.di

import app.woovictory.forearthforus.api.ApiService
import app.woovictory.forearthforus.data.repository.LoginRepository
import app.woovictory.forearthforus.data.repository.SignUpRepository
import app.woovictory.forearthforus.data.source.LoginRemoteDataSource
import app.woovictory.forearthforus.data.source.SignUpRemoteDataSource
import org.koin.dsl.module.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Created by VictoryWoo
 */

val apiModule = module {
    single<ApiService> {
        Retrofit.Builder()
            .baseUrl("http://looksgoood.pythonanywhere.com")
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiService::class.java)
    }
}

val sourceModule = module {
    factory { LoginRemoteDataSource(get()) }
    factory { SignUpRemoteDataSource(get()) }

}

val repositoryModule = module {
    factory {
        SignUpRepository(get())
    }
    factory {
        LoginRepository(get())
    }
}

var appModules = listOf(apiModule, sourceModule, viewModelModule, repositoryModule)