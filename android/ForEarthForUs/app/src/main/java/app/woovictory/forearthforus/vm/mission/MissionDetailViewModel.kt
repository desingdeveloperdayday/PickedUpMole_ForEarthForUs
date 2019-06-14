package app.woovictory.forearthforus.vm.mission

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import app.woovictory.forearthforus.base.BaseViewModel
import app.woovictory.forearthforus.data.repository.mission.MissionDetailRepository
import app.woovictory.forearthforus.model.mission.MissionDetailResponse
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import retrofit2.http.Url

/**
 * Created by VictoryWoo
 */
class MissionDetailViewModel(private val missionDetailRepository: MissionDetailRepository) : BaseViewModel() {

    private val _missionDetailResponse = MutableLiveData<MissionDetailResponse>()
    val missionDetailResponse: LiveData<MissionDetailResponse>
        get() = _missionDetailResponse

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

                }, {

                })
        )
    }

    override fun onCleared() {
        super.onCleared()
    }

}