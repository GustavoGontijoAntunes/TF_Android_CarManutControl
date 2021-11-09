package com.example.tf_carmanutcontrol_gustavoantunes

import androidx.lifecycle.*
import com.example.tf_carmanutcontrol_gustavoantunes.db.Manutencao
import com.example.tf_carmanutcontrol_gustavoantunes.db.Repositorio
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch


class MyViewModel(private val repositorio: Repositorio) : ViewModel() {
    val manutencoes = repositorio.listaManutencoes
    private var manutencaoUpdateDelete: Manutencao?

    val inputDescricao = MutableLiveData<String?>()
    val inputData = MutableLiveData<String?>()
    val inputValue = MutableLiveData<String?>()
    val inputBusca = MutableLiveData<String?>()

    val onClear = MutableLiveData<Boolean>()
    private var isUpdateOrDelete = false

    val saveUpdateBtnTxt = MutableLiveData<String>()
    val clearDeleteBtnTxt = MutableLiveData<String>()

    init {
        manutencaoUpdateDelete = null
        saveUpdateBtnTxt.value = "Listar"
        clearDeleteBtnTxt.value = "Limpar"
        onClear.value = false
    }

    fun insert(manutencao: Manutencao): Job =
        viewModelScope.launch {
            repositorio.insert(manutencao)
        }

    fun clearAll(): Job =
        viewModelScope.launch {
            repositorio.clearAll()
        }

    fun delete(manutencao: Manutencao): Job =
        viewModelScope.launch {
            repositorio.delete(manutencao)

            inputDescricao.value = null
            inputData.value = null
            inputValue.value = null
            saveUpdateBtnTxt.value = "Listar"
            clearDeleteBtnTxt.value = "Limpar"
            manutencaoUpdateDelete = null
            isUpdateOrDelete = false
        }

    fun update(manutencao: Manutencao): Job =
        viewModelScope.launch {
            repositorio.update(manutencao)

            inputDescricao.value = null
            inputData.value = null
            inputValue.value = null
            saveUpdateBtnTxt.value = "Listar"
            clearDeleteBtnTxt.value = "Limpar"
            manutencaoUpdateDelete = null
            isUpdateOrDelete = false
        }

    fun search(): Job =
        viewModelScope.launch {
            var manutencaoEncontrada = repositorio.selectManutencao(inputBusca.value.toString())

            if(manutencaoEncontrada != null){
                startUpdateDelete(manutencaoEncontrada)
            }
        }

    fun startUpdateDelete(manutencao: Manutencao) {
        inputDescricao.value = manutencao.description
        inputData.value = manutencao.date
        inputValue.value = manutencao.value

        manutencaoUpdateDelete = manutencao
        saveUpdateBtnTxt.value = "Alterar"
        clearDeleteBtnTxt.value = "Deletar"
        isUpdateOrDelete = true
    }

    fun saveUpdate() {
        if (isUpdateOrDelete) {
                manutencaoUpdateDelete?.description = inputDescricao.value!!
                manutencaoUpdateDelete?.date = inputData.value!!
                manutencaoUpdateDelete?.value = inputValue.value!!
                update(manutencaoUpdateDelete!!)
        } else {
            val description = inputDescricao.value!!
            val date = inputData.value!!
            val value = inputValue.value!!

            insert(Manutencao(0, description, date, value))

            inputDescricao.value = null
            inputData.value = null
            inputValue.value = null
        }
    }

    fun clearOrDelete() {
        if(isUpdateOrDelete){
            delete(manutencaoUpdateDelete!!)
        } else {
            setOnClearState(true)
        }
    }

    fun setOnClearState(state: Boolean) {
        onClear.value = state
    }
}

// ViewModelFactory :  Uma classe que cria ViewModels (BOILERPLATE)
class ViewModelFactory(private val repositorio: Repositorio) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(MyViewModel::class.java)){
            return MyViewModel(repositorio) as T // Retorna a ViewModel
        }
        throw IllegalArgumentException("ViewModel Desconhecida")
    }
}