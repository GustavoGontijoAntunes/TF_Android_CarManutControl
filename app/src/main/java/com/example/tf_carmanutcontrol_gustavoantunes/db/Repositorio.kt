package com.example.tf_carmanutcontrol_gustavoantunes.db

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class Repositorio(private val dao: ManutencaoDAO) {

    val listaManutencoes = dao.listarManutencoes()

    suspend fun insert(manutencao: Manutencao){
        dao.inserirManutencao(manutencao)
    }

    suspend fun update(manutencao: Manutencao){
        dao.updateManutencao(manutencao)
    }

    suspend fun delete(manutencao: Manutencao){
        dao.deleteManutencao(manutencao)
    }

    suspend fun clearAll(){
        dao.deleteAll()
    }

    suspend fun selectManutencao(busca: String) : Manutencao?{
        return withContext(Dispatchers.IO){
            return@withContext dao.buscarManutencao(busca)
        }
    }
}