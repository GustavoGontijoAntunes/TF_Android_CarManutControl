package com.example.tf_carmanutcontrol_gustavoantunes.View

import android.app.AlertDialog
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.tf_carmanutcontrol_gustavoantunes.MyViewModel
import com.example.tf_carmanutcontrol_gustavoantunes.R
import com.example.tf_carmanutcontrol_gustavoantunes.ViewModelFactory
import com.example.tf_carmanutcontrol_gustavoantunes.databinding.FragmentInsertBinding
import com.example.tf_carmanutcontrol_gustavoantunes.db.MyDatabase
import com.example.tf_carmanutcontrol_gustavoantunes.db.Repositorio


class InsertFragment : Fragment() {
    private lateinit var binding: FragmentInsertBinding

    lateinit var viewModel: MyViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_insert, container, false)

        val dao = MyDatabase.getInstance(requireContext()).manutencaoDAO
        val repositorio = Repositorio(dao)
        val factory = ViewModelFactory(repositorio)

        viewModel = ViewModelProvider(requireActivity(), factory).get(MyViewModel::class.java)

        binding.viewModelTag = viewModel // Vincula o ViewModel a tag <data>
        binding.lifecycleOwner = activity

        binding.btnSeeList.setOnClickListener {
            findNavController().navigate(R.id.action_insertFragment_to_listFragment)
        }

        displayManutencoes()

        return binding.root
    }

    private fun displayManutencoes(){
        viewModel.manutencoes.observe(viewLifecycleOwner, Observer{
            Log.i("MYTAG", it.toString())
        })

        viewModel.onClear.observe(viewLifecycleOwner, Observer {
            if(it == true){
                alertaLimparLista()
            }
        })
    }

    fun alertaLimparLista() {
        var alert = AlertDialog.Builder(context)
        alert.setTitle(getString(R.string.removeManutQuestion))
        alert.setMessage(getString(R.string.removeManutWarning))
        alert.setCancelable(false)
        alert.setPositiveButton(
            getString(R.string.confirm)
        ) { dialog, which ->
            viewModel.clearAll()
            Toast.makeText(context, getString(R.string.removeManut), Toast.LENGTH_LONG).show()
            viewModel.setOnClearState(false)
        }
        alert.setNegativeButton(
            getString(R.string.cancel)
        ) { dialog, which ->
            viewModel.setOnClearState(false)
        }
        val alertDialog = alert.create()
        alertDialog.show()
    }
}