package com.example.tf_carmanutcontrol_gustavoantunes.db

import androidx.room.*
import java.sql.Date

@Entity(tableName = "Manutencao")
data class Manutencao(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "_id")
    var id: Int,

    @ColumnInfo(name = "_description")
    var description: String,

    @ColumnInfo(name = "_date")
    var date: String?,

    @ColumnInfo(name = "_value")
    var value: String,
) {
}