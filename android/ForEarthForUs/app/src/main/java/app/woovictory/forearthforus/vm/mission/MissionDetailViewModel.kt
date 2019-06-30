package app.woovictory.forearthforus.vm.mission

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import app.woovictory.forearthforus.base.BaseViewModel
import app.woovictory.forearthforus.data.repository.mission.MissionDetailRepository
import app.woovictory.forearthforus.data.repository.mission.MissionSelectRepository
import app.woovictory.forearthforus.model.mission.MissionDetailResponse
import app.woovictory.forearthforus.model.mission.MissionFeedResponse
import app.woovictory.forearthforus.model.mission.MissionSelectRequest
import app.woovictory.forearthforus.util.SingleLiveEvent
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 * Created by VictoryWoo
 */
class MissionDetailViewModel(
    private val missionDetailRepository: MissionDetailRepository
    , private val missionSelectRepository: MissionSelectRepository
) : BaseViewModel() {

    private val _missionDetailResponse = MutableLiveData<MissionDetailResponse>()
    val missionDetailResponse: LiveData<MissionDetailResponse>
        get() = _missionDetailResponse

    private val _clickToMissionSelect = SingleLiveEvent<Any>()
    val clickToMissionSelect: LiveData<Any>
        get() = _clickToMissionSelect

    private val _missionFeedResponse = MutableLiveData<MissionFeedResponse>()
    val missionFeedResponse: LiveData<MissionFeedResponse>
        get() = _missionFeedResponse


    fun getMissionDetailInformation(token: String, categoryId: Int) {
        addDisposable(
            missionDetailRepository.getMissionDetailInformation(token, categoryId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ response ->
                    if (response.isSuccessful) {
                        response?.let { it ->
                            _missionDetailResponse.value = it.body()
                            Log.v("2285", it.code().toString())
                            //var url = Url(it.body()?.image)
                        }
                    }
                }, { error ->
                    Log.v("MissionDetail Error", error.message)
                })
        )
    }

    fun postMissionSelect(token: String, mission: MissionSelectRequest) {
        addDisposable(
            missionSelectRepository.selectNewMission(token, mission)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ response ->
                    if (response.isSuccessful) {
                        Log.v("2285223", response.code().toString())
                        _missionFeedResponse.value = response.body()

                    }
                }, { e ->
                    Log.v("2285223", e.message)
                })
        )
    }

    fun clickToMissionSelect() {
        _clickToMissionSelect.call()
    }


    override fun onCleared() {
        super.onCleared()
    }


}