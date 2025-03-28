package com.example.appgk.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class PhuongTrinhViewModel : ViewModel() {
    private val _result = MutableLiveData<String>()
    val result: LiveData<String> get() = _result

    fun solveEquation(a: Float?, b: Float?) {
        _result.value = when {
            a?.toDouble() == 0.0 && b?.toDouble() == 0.0 -> "Phương trình có vô số nghiệm"
            a?.toDouble() == 0.0 -> "Phương trình vô nghiệm"
            else -> "Nghiệm x = ${-b!! / a!!}"
        }
    }
}