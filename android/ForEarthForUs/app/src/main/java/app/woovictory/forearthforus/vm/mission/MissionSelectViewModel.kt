package app.woovictory.forearthforus.vm.mission

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import app.woovictory.forearthforus.base.BaseViewModel
import app.woovictory.forearthforus.data.repository.mission.MissionSelectListRepository
import app.woovictory.forearthforus.model.mission.MissionSelectResponse
import app.woovictory.forearthforus.util.SharedPreferenceManager
import app.woovictory.forearthforus.util.SingleLiveEvent
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 * Created by VictoryWoo
 */
class MissionSelectViewModel(private val missionSelectListRepository: MissionSelectListRepository) : BaseViewModel() {

    private val _clickToMissionStart = SingleLiveEvent<Any>()
    val clickToMissionStart: LiveData<Any>
        get() = _clickToMissionStart

    private val _missionSelectResponse = MutableLiveData<ArrayList<MissionSelectResponse>>()
    val missionSelectResponse: LiveData<ArrayList<MissionSelectResponse>>
        get() = _missionSelectResponse

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean>
        get() = _isLoading


    init {
        _isLoading.value = true
    }

    fun clickToMissionStart() {
        _clickToMissionStart.call()
    }


    fun getMissionSelectList(categoryId: Int) {
        _isLoading.value = true

        addDisposable(
            missionSelectListRepository.getMissionSelectList(SharedPreferenceManager.token, categoryId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ response ->
                    if (response.isSuccessful) {
                        response?.let {
                            Log.v("228874", it.code().toString())
                            _missionSelectResponse.value = it.body()
                            //_missionSelectResponse.value?.get(0)?.image = "http://upload.wikimedia.org/wikipedia/commons/e/e8/Svg_example3.svg"
                        }
                    }
                    _isLoading.value = false
                }, { e ->
                    Log.v("228874", e.message)
                    _isLoading.value = true
                })
        )
    }

}