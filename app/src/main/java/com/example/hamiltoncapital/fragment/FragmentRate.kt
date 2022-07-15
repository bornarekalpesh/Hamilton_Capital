package com.example.hamiltoncapital.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.Nullable
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.example.ResponseExchangeRate
import com.example.hamiltoncapital.App
import com.example.hamiltoncapital.R
import com.example.hamiltoncapital.databinding.FragmentRateBinding
import com.example.hamiltoncapital.repository.ExchangeRateRepository
import com.example.hamiltoncapital.utils.Resource
import com.example.hamiltoncapital.viewmodel.ExchangeRateViewModel
import com.example.hamiltoncapital.viewmodel.ExchangeRateViewModelFactory
import com.google.gson.Gson
import com.skydoves.powerspinner.OnSpinnerItemSelectedListener
import org.json.JSONObject


class FragmentRate : Fragment() {

    lateinit var binding:FragmentRateBinding

    lateinit var currenyListName:ArrayList<String>

    var currency1:String="";
    var currency2:String="";

    var currency1Rate:String="";
    var currency2Rate:String="";

    lateinit var jsonObject:JSONObject;
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding= DataBindingUtil.inflate(inflater,R.layout.fragment_rate,container,false);
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        currenyListName=ArrayList<String>();
        binding.btnCalulate.setOnClickListener {
            if(!binding.currencyValue.text.isNullOrBlank()){
                findNavController().navigate(
                    FragmentRateDirections.actionFragmentRateToFragmentCurrency()
                        .setCurrency1Rate(currency1Rate)
                        .setCureency2Rate(currency2Rate)
                        .setCurreny1name(currency1)
                        .setCurrency2name(currency2)
                        .setAddedCurrency(binding.currencyValue.text.toString())
                )
            }else{
                Toast.makeText(requireActivity(),"Please Enter Currency",Toast.LENGTH_LONG).show()
            }


        }
        val repository=(requireContext().applicationContext as App).repository as ExchangeRateRepository
        var viewModel= ViewModelProvider(this,
            ExchangeRateViewModelFactory(repository)
        ).get(ExchangeRateViewModel::class.java)



        binding.spinnerCurrency1.setOnSpinnerItemSelectedListener(object :
            OnSpinnerItemSelectedListener<String?> {
            override fun onItemSelected(
                oldIndex: Int,
                oldItem: String?,
                newIndex: Int,
                newItem: String?
            ) {
                currency1=newItem.toString();
                currency1Rate=jsonObject.getString(newItem.toString())

                Log.e("Curreny1","Curreny 1"+currency1+"- Currency1 Rate"+jsonObject.getString(newItem.toString()));
            }
        })
        binding.spinnerCurrency2.setOnSpinnerItemSelectedListener(object :
            OnSpinnerItemSelectedListener<String?> {
            override fun onItemSelected(
                oldIndex: Int,
                oldItem: String?,
                newIndex: Int,
                newItem: String?
            ) {
                currency2=newItem.toString();
                currency2Rate=jsonObject.getString(newItem.toString())
                viewModel.getExchangeRate(currency1)

                currency1=binding.spinnerCurrency1.text.toString()
                currency1Rate=jsonObject.getString(binding.spinnerCurrency1.text.toString())

                currency2=newItem.toString()
                currency2Rate=jsonObject.getString(newItem.toString());

                Log.e("-----------------","----------------------------------------");
                Log.e("Update Value Currency1","Currency 1"+currency1+
                        "- Currency1 Rate"+currency1Rate);

                Log.e("Update Value Currency2","Currency 2"+currency2+
                        "- Currency2 Rate"+currency2Rate);
            }
        })
        viewModel.getExchangeRate("USD")
        viewModel.responseExchangeRate.observe(viewLifecycleOwner, Observer {
            it.getContentIfNotHandled().let {
                when(it){
                    is Resource.Loading->{
                        Log.e("Loading","");
                    }
                    is Resource.Success->{
                        val gson = Gson()
                        val json = gson.toJson(it.data!!.conversionRates!!)

                        jsonObject=JSONObject(json)
                        jsonObject.keys().forEach {
                          //  Log.e("KEY",""+it)
                            currenyListName.add(""+it);
                        }

                        binding.spinnerCurrency1.setItems(currenyListName.distinct())
                        binding.spinnerCurrency2.setItems(currenyListName.distinct())
                        binding.spinnerCurrency1.lifecycleOwner=viewLifecycleOwner
                        binding.spinnerCurrency2.lifecycleOwner=viewLifecycleOwner

                    }
                    is Resource.Failure->{
                        Log.e("Failer","");
                    }
                }
            }

        })
    }
}