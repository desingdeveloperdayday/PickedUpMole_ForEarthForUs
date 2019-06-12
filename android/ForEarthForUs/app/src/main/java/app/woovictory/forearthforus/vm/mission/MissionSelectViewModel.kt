package app.woovictory.forearthforus.vm.mission

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import app.woovictory.forearthforus.base.BaseViewModel
import app.woovictory.forearthforus.data.repository.mission.MissionSelectRepository
import app.woovictory.forearthforus.model.mission.MissionSelectResponsee
import app.woovictory.forearthforus.util.SharedPreferenceManager
import app.woovictory.forearthforus.util.SingleLiveEvent
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 * Created by VictoryWoo
 */
class MissionSelectViewModel(private val missionSelectRepository: MissionSelectRepository) : BaseViewModel() {

    private val _clickToMissionStart = SingleLiveEvent<Any>()
    val clickToMissionStart: LiveData<Any>
        get() = _clickToMissionStart

    private val _missionSelectResponse = MutableLiveData<ArrayList<MissionSelectResponsee>>()
    val missionSelectResponse: LiveData<ArrayList<MissionSelectResponsee>>
        get() = _missionSelectResponse

    fun clickToMissionStart() {
        _clickToMissionStart.call()
    }


    fun getMissionSelectList(categoryId: Int) {
        addDisposable(missionSelectRepository.getMissionSelectList(SharedPreferenceManager.token, categoryId)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ response ->
                if (response.isSuccessful) {
                    response?.let {
                        Log.v("228874", it.code().toString())
                        _missionSelectResponse.value = it.body()
                    }
                }
            }, { e ->
                Log.v("228874", e.message)
            })
        )
    }

}