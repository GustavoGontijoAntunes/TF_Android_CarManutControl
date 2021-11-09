package com.example.tf_carmanutcontrol_gustavoantunes.ATemplates

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.tf_carmanutcontrol_gustavoantunes.MyViewModel
import com.example.tf_carmanutcontrol_gustavoantunes.db.Repositorio
import java.lang.IllegalArgumentException

class MyViewModelFactory(private val repositorio: Repositorio) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(MyViewModel::class.java)){
            return MyViewModel(repositorio) as T // Retorna a ViewModel
        }
        throw IllegalArgumentException("Unknown View Model class")
    }
}