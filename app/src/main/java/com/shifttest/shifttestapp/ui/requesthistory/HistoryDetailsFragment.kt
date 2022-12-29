package com.shifttest.shifttestapp.ui.requesthistory

import android.content.ContentValues
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import com.shifttest.shifttestapp.databinding.FragmentHistoriRequestBinding
import com.shifttest.shifttestapp.databinding.FragmentHystoryDetailsBinding
import com.shifttest.shifttestapp.repository.GlobalApplication
import com.shifttest.shifttestapp.ui.carddatarequest.carddataactivity.CardDataViewModel
import com.shifttest.shifttestapp.ui.carddatarequest.carddataactivity.CardDataViewModelFactory

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [HistoryDetailsFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class HistoryDetailsFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private val vModelActivityRequest: CardDataViewModel by viewModels {
        CardDataViewModelFactory((activity?.application as GlobalApplication).repository)
    }
    lateinit var binding: FragmentHystoryDetailsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHystoryDetailsBinding.inflate(inflater)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val bundle = arguments
        var recieveInfo = 0
        if (bundle != null) {
            recieveInfo = bundle.getInt("id")
        }
        vModelActivityRequest.data.observe(activity as LifecycleOwner, Observer {
            val data = it.get(recieveInfo)
            binding.binCard.text = data.bin
            binding.network.text = data.scheme
            if(data.prepaid == true) binding.prepayment.text = "Yes" else binding.prepayment.text = "No"
            binding.brand.text = data.brand
            binding.country.text = data.country?.emoji + " " + data.country?.name
            val latitude = data.country?.latitude
            val longitude = data.country?.longitude
            binding.coordinates.text = "(latitude: $latitude, longitude: $longitude)"
            binding.lengthNumber.text = data.number?.length.toString()
            if(data.number?.luhn == true) binding.luhn.text = "Yes" else binding.luhn.text = "No"
            binding.typeCard.text = data.type
            binding.bankName.text = data.bank?.nameBank + ", " + data.bank?.city
            binding.banklink.text = data.bank?.url
            binding.bankPhone.text = data.bank?.phone

            binding.coordinates.setOnClickListener {
                val geoUriString = "geo:$latitude,$longitude"
                val geoUri: Uri = Uri.parse(geoUriString)
                val mapIntent = Intent(Intent.ACTION_VIEW, geoUri)
                startActivity(mapIntent)

            }
        })

        vModelActivityRequest.errorMessage.observe(activity as LifecycleOwner, Observer {
            Log.d(ContentValues.TAG, "errorMessage: $it")
        })
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment HystoryDetailsFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance() =
            HistoryDetailsFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}