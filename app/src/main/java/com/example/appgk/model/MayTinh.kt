package com.example.appgk.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "MayTinh")
data class MayTinh(
    @PrimaryKey(autoGenerate = true) val mamay: Int = 0,
    val tenmay: String,
    val loaimay: String,
    val soluong: Int,
    val dongia: Int
) {
    val thanhTien: Int
        get() = soluong * dongia
}
