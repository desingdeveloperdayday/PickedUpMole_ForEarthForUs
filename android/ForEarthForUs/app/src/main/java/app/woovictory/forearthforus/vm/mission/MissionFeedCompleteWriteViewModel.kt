package app.woovictory.forearthforus.vm.mission

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import app.woovictory.forearthforus.base.BaseViewModel
import app.woovictory.forearthforus.data.repository.mission.MissionFeedCompleteRepository
import app.woovictory.forearthforus.model.mission.MissionFeedCompleteRequest
import app.woovictory.forearthforus.model.mission.MissionFeedResponse
import app.woovictory.forearthforus.util.SingleLiveEvent
import app.woovictory.forearthforus.util.TAG
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 * Created by VictoryWoo
 */
class MissionFeedCompleteWriteViewModel(private val missionFeedCompleteRepository: MissionFeedCompleteRepository) :
    BaseViewModel() {

    private val _clickToBackMission = SingleLiveEvent<Any>()
    val clickToBackMission: LiveData<Any>
        get() = _clickToBackMission

    private val _clickToComplete = SingleLiveEvent<Any>()
    val clickToComplete: LiveData<Any>
        get() = _clickToComplete

    private val _missionFeedCompleteResponse = MutableLiveData<MissionFeedResponse>()
    val missionFeedCompleteResponse: LiveData<MissionFeedResponse>
        get() = _missionFeedCompleteResponse

    fun clickToBackMission() {
        _clickToBackMission.call()
    }

    fun clickToComplete() {
        _clickToComplete.call()
    }

    fun completeMission(token: String, missionFeedCompleteRequest: MissionFeedCompleteRequest, id: String) {
        addDisposable(
            missionFeedCompleteRepository.completeMissionFeed(token, missionFeedCompleteRequest, id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ response ->
                    if (response.isSuccessful) {
                        response.body()?.let {
                            _missionFeedCompleteResponse.value = it
                            Log.v("228173", it.mission.status)
                        }
                    }
                    Log.v("228173", response.message())
                }, { error ->
                    Log.v("228173", error.message)
                })
        )
    }
}