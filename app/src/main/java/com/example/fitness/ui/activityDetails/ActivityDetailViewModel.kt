package com.example.fitness.ui.activityDetails

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ActivityDetailViewModel : ViewModel() {

    private val _activity = MutableLiveData<String>().apply {
        value = "Activity Name"
    }
    val activity: LiveData<String> = _activity

}