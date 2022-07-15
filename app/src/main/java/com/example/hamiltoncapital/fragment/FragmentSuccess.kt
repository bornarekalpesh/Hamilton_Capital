package com.example.hamiltoncapital.fragment

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.navArgs
import com.example.hamiltoncapital.R
import com.example.hamiltoncapital.activity.MainActivity
import com.example.hamiltoncapital.databinding.FragmentSuccessBinding


class FragmentSuccess : Fragment() {

    private val args:FragmentSuccessArgs by navArgs()

    lateinit var binding:FragmentSuccessBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding= DataBindingUtil.inflate(inflater,R.layout.fragment_success,container,false);
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.message.setText("Great now you have ${args.message} in your Account")

        binding.reuse.setOnClickListener {
            val intent = Intent(requireActivity(), MainActivity::class.java)
            startActivity(intent)
            requireActivity().finish()
        }
    }
}