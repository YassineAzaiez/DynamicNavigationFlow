package com.skoove.app.presentation

import androidx.lifecycle.MutableLiveData

class ToolbarShared {

     var toolbarTitle = MutableLiveData<String>()

    companion object {
        @Volatile
        var INSTANCE: ToolbarShared? = null
        fun getInstance(): ToolbarShared = INSTANCE ?: synchronized(this) {
            ToolbarShared().also {
                INSTANCE = it
            }
        }

    }

    fun updateTitle(title : String?){
        toolbarTitle.postValue(title)
    }
}