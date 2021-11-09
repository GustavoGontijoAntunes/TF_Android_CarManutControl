package com.example.tf_carmanutcontrol_gustavoantunes.View

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import com.example.tf_carmanutcontrol_gustavoantunes.R
import com.example.tf_carmanutcontrol_gustavoantunes.databinding.FragmentStartBinding

class StartFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding: FragmentStartBinding = DataBindingUtil.inflate(inflater,
            R.layout.fragment_start, container, false)

        binding.btnStart.setOnClickListener(){ view : View ->
            view.findNavController().navigate(StartFragmentDirections.actionStartFragmentToInsertFragment())
        }

        return binding.root
    }
}