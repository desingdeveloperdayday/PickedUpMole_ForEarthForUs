package app.woovictory.forearthforus.vm.main

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import app.woovictory.forearthforus.base.BaseViewModel
import app.woovictory.forearthforus.data.repository.feed.MissionFeedRepository
import app.woovictory.forearthforus.data.repository.main.EarthRepository
import app.woovictory.forearthforus.model.earth.EarthResponse
import app.woovictory.forearthforus.model.mission.MissionFeedResponse
import app.woovictory.forearthforus.util.MISSION_STATUS_PROGRESS
import app.woovictory.forearthforus.util.SharedPreferenceManager
import app.woovictory.forearthforus.util.SingleLiveEvent
import app.woovictory.forearthforus.util.TAG
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.Observables
import io.reactivex.schedulers.Schedulers

/**
 * Created by VictoryWoo
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
                        Log.v("12301 ${it.first.body()?.earthLevel}", it.first.body()?.earthLevel.toString())
                    }
                    Log.v("12301 s", "sdasd")
                    _isLoading.value = false
                }, { error ->
                    Log.v("12301 f", error.message)
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
                            Log.v("9903021", it.toString())
                            _earthUserResponse.value = it
                        }
                    }
                }, { error ->
                    Log.v("9903021", error.message)
                })
        )
    }

}