package com.milkyway.milkyway.ui.universe

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.milkyway.milkyway.data.remote.RetrofitBuilder
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.HttpException

class UniverseBottomSheetViewModel : ViewModel() {

    private val _deleteUniverse: MutableLiveData<Boolean> = MutableLiveData(false)
    val deleteUniverse: LiveData<Boolean>
        get() = _deleteUniverse

    private val _confirmDelete: MutableLiveData<Boolean> = MutableLiveData(false)
    val confirmDelete: LiveData<Boolean>
        get() = _confirmDelete

    val clickCafeId: MutableLiveData<Int> = MutableLiveData(0)


    fun setDeleteUniverseClick() {
        _deleteUniverse.value = !_deleteUniverse.value!!
    }

    fun setConfirmDeleteClick() {
        _confirmDelete.value = !_confirmDelete.value!!
    }

    fun requestDeleteUniverse(token: String) = viewModelScope.launch(Dispatchers.IO) {

        try {
            val delete = RetrofitBuilder.service.deleteUniverse(
                token, clickCafeId.value!!)

            Log.d("response universe", delete.toString())

        } catch (e: HttpException) {
            Log.d("request", e.toString())
            Log.d("requestMessage", e.response().toString())
        }
    }
}