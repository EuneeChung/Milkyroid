package com.milkyway.milkyway.ui.universe

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class UniverseBottomSheetViewModel :ViewModel(){

    private val _deleteUniverse: MutableLiveData<Boolean> = MutableLiveData(false)
    val deleteUniverse: LiveData<Boolean>
        get() = _deleteUniverse

    private val _confirmDelete: MutableLiveData<Boolean> = MutableLiveData(false)
    val confirmDelete: LiveData<Boolean>
        get() = _confirmDelete

    fun setDeleteUniverseClick() {
        _deleteUniverse.value = !_deleteUniverse.value!!
    }

    fun setConfirmDeleteClick() {
        _confirmDelete.value = !_confirmDelete.value!!
    }
}