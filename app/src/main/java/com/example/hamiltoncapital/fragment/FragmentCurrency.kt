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
import com.example.hamiltoncapital.databinding.FragmentCurrencyBinding


class FragmentCurrency : Fragment() {

    lateinit var binding:FragmentCurrencyBinding

    private val args:FragmentCurrencyArgs by navArgs()
    var currency1:String="";
    var currency2:String="";

    var currency1Rate:String="";
    var currency2Rate:String="";

    var currencyAmount:String="";
    var tempAmt:Double=0.0;

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding= DataBindingUtil.inflate(inflater,R.layout.fragment_currency,container,false);
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
         currency1=args.curreny1name;
         currency2=args.currency2name;

         currency1Rate=args.currency1Rate;
         currency2Rate=args.cureency2Rate;
         currencyAmount=args.addedCurrency;

        binding.currency1Name.setText(""+currencyAmount+currency1)
        tempAmt=currencyAmount.toDouble()*currency2Rate.toDouble()

        binding.currency2Name.setText(""+tempAmt+""+currency2)

        binding.btnConvert.setOnClickListener {
            it.findNavController().navigate(
                FragmentCurrencyDirections.actionFragmentCurrencyToFragmentApproval()
                    .setCurrency2(""+tempAmt+currency2)
                    .setCurreny1(""+currencyAmount+currency1)
            )
        }

        object : CountDownTimer(30000, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                binding.counttime.setText(""+ millisUntilFinished / 1000)
            }
            override fun onFinish() {
                binding.counttime.setText("done!")
                    binding.btnConvert.visibility=View.GONE;
            }
        }.start()


    }
}