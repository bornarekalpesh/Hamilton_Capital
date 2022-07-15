package com.example.hamiltoncapital.fragment

import android.os.Bundle
import android.os.CountDownTimer
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import androidx.navigation.fragment.navArgs
import com.example.hamiltoncapital.R
import com.example.hamiltoncapital.databinding.FragmentFragementApprovalBinding


class FragmentApproval : Fragment() {

    lateinit var binding:FragmentFragementApprovalBinding

    private val args:FragmentApprovalArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding= DataBindingUtil.inflate(inflater,R.layout.fragment_fragement_approval,container,false);
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.message.setText("You are about to get ${args.currency2} for ${args.curreny1}.Do you want to approve transcation")
        //
        binding.btnApprove.setOnClickListener {
            it.findNavController().navigate(
                FragmentApprovalDirections.actionFragmentApprovalToFragmentSuccess()
                    .setMessage(""+args.currency2)
            )
        }

        object : CountDownTimer(30000, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                binding.counttime.setText(""+ millisUntilFinished / 1000)
            }
            override fun onFinish() {
                binding.counttime.setText("done!")
                binding.btnApprove.visibility=View.GONE;
              //  Toast.makeText(requireActivity(),"You Exceeded Time Limit", Toast.LENGTH_LONG).show()

            }
        }.start()

        binding.ivBack.setOnClickListener{
//            it.findNavController().navigate(
//                FragmentApprovalDirections.ActionFragmentApprovalToFragmentCurrency()
//            )
        }
    }
}