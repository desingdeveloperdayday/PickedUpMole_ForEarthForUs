package app.woovictory.forearthforus.vm.mypage

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import app.woovictory.forearthforus.base.BaseViewModel
import app.woovictory.forearthforus.data.repository.feed.MissionFeedRepository
import app.woovictory.forearthforus.model.mission.MissionFeedResponse
import app.woovictory.forearthforus.util.SingleLiveEvent
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 * Created by VictoryWoo
 */
class AchieveListViewModel(private val missionFeedRepository: MissionFeedRepository) : BaseViewModel() {

    private val _clickToBack = SingleLiveEvent<Any>()
    val clickToBack: LiveData<Any>
        get() = _clickToBack

    private val _achieveListResponse = MutableLiveData<List<MissionFeedResponse>>()
    val achieveListResponse: LiveData<List<MissionFeedResponse>>
        get() = _achieveListResponse

    fun clickToBack() {
        _clickToBack.call()
    }

    fun getAchieveList(token: String, progress: String) {
        addDisposable(
            missionFeedRepository.getUserMissionFeed(token, progress)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ response ->
                    if (response.isSuccessful) {
                        response.body()?.let {
                            Log.v("a3302", it.size.toString())
                            Log.v("a3302", response.code().toString())
                            _achieveListResponse.value = it
                        }
                    }
                }, { error ->
                    Log.v("a3302", error.message)
                })
        )
    }
}