package com.example.tf_carmanutcontrol_gustavoantunes.View

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.tf_carmanutcontrol_gustavoantunes.MyViewModel
import com.example.tf_carmanutcontrol_gustavoantunes.R
import com.example.tf_carmanutcontrol_gustavoantunes.ViewModelFactory
import com.example.tf_carmanutcontrol_gustavoantunes.databinding.FragmentListBinding
import com.example.tf_carmanutcontrol_gustavoantunes.db.MyDatabase
import com.example.tf_carmanutcontrol_gustavoantunes.db.Repositorio

class ListFragment : Fragment() {
    private lateinit var binding: FragmentListBinding
    lateinit var viewModel: MyViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_list, container, false)

        val dao = MyDatabase.getInstance(requireContext()).manutencaoDAO
        val repositorio = Repositorio(dao)
        val factory = ViewModelFactory(repositorio)

        viewModel = ViewModelProvider(requireActivity(), factory).get(MyViewModel::class.java)

        viewModel.manutencoes.observe(viewLifecycleOwner, Observer{
            var strListaManutencoes = ""
            var somaManutencoes = 0
            it.forEach{
                somaManutencoes++
                strListaManutencoes += "${somaManutencoes.toString()} - \nDescrição: ${it.description}\nData: ${it.date}\nValor: R$ ${it.value}\n\n"
            }

            binding.txtFilledList.text = strListaManutencoes
        })

        binding.viewModelList = viewModel

        return binding.root
    }
}