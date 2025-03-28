package com.example.appgk.database

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.appgk.model.MayTinh

@Dao
interface MayTinhDao {
    @Query("SELECT * FROM MayTinh")
    fun getAllMayTinh(): LiveData<List<MayTinh>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMayTinh(mayTinh: MayTinh)

    @Update
    suspend fun updateMayTinh(mayTinh: MayTinh)

    @Delete
    suspend fun deleteMayTinh(mayTinh: MayTinh)
}
