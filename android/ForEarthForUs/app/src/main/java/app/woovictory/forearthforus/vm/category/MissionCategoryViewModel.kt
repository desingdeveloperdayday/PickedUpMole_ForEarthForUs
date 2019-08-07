package app.woovictory.forearthforus.vm.category

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import app.woovictory.forearthforus.base.BaseViewModel
import app.woovictory.forearthforus.data.repository.category.MissionCategoryRepository
import app.woovictory.forearthforus.model.category.MissionCategoryResponse
import app.woovictory.forearthforus.data.SharedPreferenceManager
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 * Created by VictoryWoo
 */
class MissionCategoryViewModel(private val missionCategoryRepository: MissionCategoryRepository) : BaseViewModel() {

    private val _missionCategoryResponse = MutableLiveData<ArrayList<MissionCategoryResponse>>()
    val missionCategoryResponse: LiveData<ArrayList<MissionCategoryResponse>>
        get() = _missionCategoryResponse

    fun getMissionCategoryList() {
        addDisposable(
            missionCategoryRepository.getMissionCategoryList(SharedPreferenceManager.token)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ response ->
                    if (response.isSuccessful) {
                        Log.v("8827 ", response.code().toString())
                        response.body()?.let {
                            Log.v("8827 ", it[0].completeMessage)
                            Log.v("8827 ", it[1].completeMessage)
                            _missionCategoryResponse.value = it
                        }

                    }
                }, { e ->
                    Log.v("8827 f", e.message)
                })
        )
    }
}