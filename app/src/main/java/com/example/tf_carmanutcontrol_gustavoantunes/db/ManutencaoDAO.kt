package com.example.tf_carmanutcontrol_gustavoantunes.db

import androidx.lifecycle.LiveData
import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface ManutencaoDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun inserirManutencao(manutencao: Manutencao)

    @Update
    suspend fun updateManutencao(manutencao: Manutencao)

    @Delete
    suspend fun deleteManutencao(manutencao: Manutencao)

    @Query("SELECT * FROM MANUTENCAO")
    fun listarManutencoes(): LiveData<List<Manutencao>>

    @Query("DELETE FROM MANUTENCAO")
    suspend fun deleteAll()

    @Query("SELECT * FROM MANUTENCAO WHERE _description LIKE '%' || :search || '%'")
    suspend fun buscarManutencao(search: String): Manutencao?

    @Query("SELECT * FROM MANUTENCAO ORDER BY _id DESC")
    fun listarManutencaoDesc(): LiveData<List<Manutencao>>

    @Query("SELECT * FROM MANUTENCAO ORDER BY _description ASC")
    fun listarManutencaoAsc(): Flow<List<Manutencao>>

    @Query("SELECT * FROM MANUTENCAO ORDER BY _id DESC LIMIT 1")
    fun getUltimoManutencao(): Manutencao?
}