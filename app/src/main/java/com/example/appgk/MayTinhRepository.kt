package com.example.appgk

import androidx.lifecycle.LiveData
import com.example.appgk.database.MayTinhDao
import com.example.appgk.model.MayTinh

class MayTinhRepository(private val mayTinhDao: MayTinhDao) {

    val allMayTinh: LiveData<List<MayTinh>> = mayTinhDao.getAllMayTinh()

    suspend fun insertMayTinh(mayTinh: MayTinh) {
        mayTinhDao.insertMayTinh(mayTinh)
    }

    suspend fun updateMayTinh(mayTinh: MayTinh) {
        mayTinhDao.updateMayTinh(mayTinh)
    }

    suspend fun deleteMayTinh(mayTinh: MayTinh) {
        mayTinhDao.deleteMayTinh(mayTinh)
    }
}