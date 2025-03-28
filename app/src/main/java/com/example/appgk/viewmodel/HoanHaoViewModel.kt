package com.example.appgk.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class HoanHaoViewModel : ViewModel() {

    private val _result = MutableLiveData<String>()
    val result: LiveData<String> get() = _result

    fun checkPerfectNumber(number: Int) {
        _result.value = if (isPerfectNumber(number)) {
            "$number là số hoàn hảo"
        } else {
            "$number không phải là số hoàn hảo"
        }
    }

    private fun isPerfectNumber(num: Int): Boolean {
        if (num < 2) return false
        var sum = 1
        for (i in 2..num / 2) {
            if (num % i == 0) sum += i
        }
        return sum == num
    }
}