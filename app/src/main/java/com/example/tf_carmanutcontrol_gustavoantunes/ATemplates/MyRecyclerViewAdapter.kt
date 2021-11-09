package com.example.tf_carmanutcontrol_gustavoantunes.ATemplates

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.DataBindingUtil.inflate
import androidx.recyclerview.widget.RecyclerView
import com.example.tf_carmanutcontrol_gustavoantunes.R
import com.example.tf_carmanutcontrol_gustavoantunes.databinding.ListItemBinding
import com.example.tf_carmanutcontrol_gustavoantunes.db.Manutencao

class MyRecyclerViewAdapter(private val listaManutencoes: List<Manutencao>, private val clickListener: (Manutencao)->Unit) :
    RecyclerView.Adapter<MyViewHolder>() { // Unit - Não tem retorno

    // Método chamado quando novos "itens" da lista (novos ViewHolder) são criados, responsável por definir também o layout a ser utilizado
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding: ListItemBinding =
            inflate(layoutInflater, R.layout.list_item, parent, false)
        return MyViewHolder(binding)
    }

    // Método responsável por "popular" a lista
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(listaManutencoes[position], clickListener)
    }

    // Método que configura a quantidade de itens do RecyclerView
    override fun getItemCount(): Int {
        return listaManutencoes.size
    }
}

// View Holder - Classe responsável por configurar as Views de cada um do itens da lista (a fôrma). É a única classe que se comunica diretamente com os Views
class MyViewHolder(val binding: ListItemBinding) : RecyclerView.ViewHolder(binding.root) {
    fun bind(subscriber: Manutencao, clickListener: (Manutencao) -> Unit) {
        binding.txtViewDesc.text = subscriber.description
        binding.txtViewDate.text = subscriber.date
        binding.txtViewValue.text = subscriber.value.toString()
        binding.listItemLayout.setOnClickListener{
            clickListener(subscriber)
        }
    }
}