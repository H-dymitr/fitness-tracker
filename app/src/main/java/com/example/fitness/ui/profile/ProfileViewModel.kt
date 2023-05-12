package com.example.fitness.ui.profile

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.fitness.utils.BMICalcUtil

class ProfileViewModel : ViewModel() {
    // Declare the data fields as MutableLiveData to allow them to be observed
    private val _name = MutableLiveData<String>()
    private val _age = MutableLiveData<Int>()
    private val _height = MutableLiveData<Int>()
    private val _weight = MutableLiveData<Int>()
    private val _BMI = MutableLiveData<String>()

    // Expose the data fields as LiveData to allow read-only access from the UI
    val name: LiveData<String> = _name
    val age: LiveData<Int> = _age
    val height: LiveData<Int> = _height
    val weight: LiveData<Int> = _weight
    val BMI: LiveData<String> = _BMI

    init {
        // get data from shared preferences
        _name.value = "Jan Kowalski"
        _age.value = 20
        _height.value = 180
        _weight.value = 80
        // calculateBMI
        val bmi = BMICalcUtil.instance.calculateBMIMetric(_height.value!!.toDouble(), _weight.value!!.toDouble())
        _BMI.value = BMICalcUtil.instance.classifyBMI(bmi) + " (${bmi})"
    }
}