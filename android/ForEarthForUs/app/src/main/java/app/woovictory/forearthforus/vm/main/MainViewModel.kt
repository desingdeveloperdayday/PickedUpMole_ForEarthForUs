package app.woovictory.forearthforus.vm.main

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import app.woovictory.forearthforus.base.BaseViewModel
import app.woovictory.forearthforus.data.repository.feed.MissionFeedRepository
import app.woovictory.forearthforus.data.repository.main.EarthRepository
import app.woovictory.forearthforus.model.earth.EarthResponse
import app.woovictory.forearthforus.model.mission.MissionFeedResponse
import app.woovictory.forearthforus.utils.MISSION_STATUS_PROGRESS
import app.woovictory.forearthforus.data.SharedPreferenceManager
import app.woovictory.forearthforus.utils.SingleLiveEvent
import app.woovictory.forearthforus.utils.TAG
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.Observables
import io.reactivex.schedulers.Schedulers

/**
 * Created by VictoryWoo
 * 서버에서 3초 이상 데이터를 받아오지 못한다면 로컬 데이터 보여주기?!
 * TODO
 * 1. 로컬에 데이터 캐싱?!
 * 2. 리프레쉬 레이아웃 추가하기.
 */
class MainViewModel(
    private val earthRepository: EarthRepository, private val missionFeedRepository
    : MissionFeedRepository
) : BaseViewModel() {

    private val _earthResponse = MutableLiveData<EarthResponse>()
    val earthResponse: LiveData<EarthResponse>
        get() = _earthResponse

    // mutable 하게 만들고, immutable 하게 노출시킨다.

    private val _clickToEarthDetail = SingleLiveEvent<Any>()
    val clickToEarthDetail: LiveData<Any>
        get() = _clickToEarthDetail

    private val _missionFeedResponse = MutableLiveData<ArrayList<MissionFeedResponse>>()
    val missionFeedResponse: LiveData<ArrayList<MissionFeedResponse>>
        get() = _missionFeedResponse

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean>
        get() = _isLoading

    private val _earthUserResponse = MutableLiveData<EarthResponse>()
    val earthUserResponse: LiveData<EarthResponse>
        get() = _earthUserResponse

    fun clickToDetail() {
        _clickToEarthDetail.call()
    }

    init {
        _isLoading.value = true
    }

    fun getInformation() {
        val earthObservable = earthRepository
            .getEarthInformation(SharedPreferenceManager.token, SharedPreferenceManager.earthLevel)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())

        val missionFeedObservable = missionFeedRepository
            .getUserMissionFeed(SharedPreferenceManager.token, MISSION_STATUS_PROGRESS)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())

        _isLoading.value = true
        addDisposable(
            Observables.zip(earthObservable, missionFeedObservable)
                .subscribe({
                    if (it.first.isSuccessful && it.second.isSuccessful) {
                        _earthResponse.value = it.first.body()
                        _missionFeedResponse.value = it.second.body()
                        SharedPreferenceManager.userContent = it.first.body()?.content!!
                        Log.v(TAG, it.second.body()?.size.toString())
                    }
                    _isLoading.value = false
                }, { error ->
                    Log.v(TAG, error.message)
                })
        )
    }

    fun getUserInformation() {
        addDisposable(
            earthRepository.getUserInformation(SharedPreferenceManager.token)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ response ->
                    if (response.isSuccessful) {
                        response.body()?.let {
                            _earthUserResponse.value = it
                        }
                    }
                }, { error ->
                    Log.d("Error", error.message)
                })
        )
    }

}