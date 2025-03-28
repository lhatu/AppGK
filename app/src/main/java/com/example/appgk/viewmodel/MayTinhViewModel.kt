package com.example.appgk.viewmodel

import android.app.Application
import androidx.lifecycle.*
import com.example.appgk.MayTinhRepository
import com.example.appgk.database.AppDatabase
import com.example.appgk.model.MayTinh
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MayTinhViewModel(application: Application) : AndroidViewModel(application) {
    private val dao = AppDatabase.getDatabase(application).mayTinhDao()
    val mayTinhList: LiveData<List<MayTinh>> = dao.getAllMayTinh()

    private val repository: MayTinhRepository
    val allMayTinh: LiveData<List<MayTinh>>

    init {
        val mayTinhDao = AppDatabase.getDatabase(application).mayTinhDao()
        repository = MayTinhRepository(mayTinhDao)
        allMayTinh = repository.allMayTinh
    }

    fun insert(mayTinh: MayTinh) = viewModelScope.launch {
        viewModelScope.launch(Dispatchers.IO) {
            repository.insertMayTinh(mayTinh)
        }
    }

    fun update(mayTinh: MayTinh) = viewModelScope.launch {
        viewModelScope.launch(Dispatchers.IO) {
            repository.updateMayTinh(mayTinh)
        }
    }

    fun delete(mayTinh: MayTinh) = viewModelScope.launch {
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteMayTinh(mayTinh)
        }
    }
}
