package com.shifttest.shifttestapp.ui.carddatarequest.carddataactivity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.shifttest.shifttestapp.R
import com.shifttest.shifttestapp.databinding.ActivityCardDataBinding
import com.shifttest.shifttestapp.ui.carddatarequest.fragmentcarddata.CardDetailsFragment
import com.shifttest.shifttestapp.ui.requesthistory.HistoryRequestFragment

class CardDataActivity : AppCompatActivity() {
    private val TAG = "CardDataActivity"
    private lateinit var binding: ActivityCardDataBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_card_data)
        binding = ActivityCardDataBinding.inflate(layoutInflater)
        setContentView(binding.root)
        startFragment(CardDetailsFragment.newInstance(), R.id.dataLayout)

        binding.bottomNav.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.history -> {
                    startFragment(HistoryRequestFragment.newInstance(), R.id.dataLayout)
                    true
                }
                R.id.search -> {
                    startFragment(CardDetailsFragment.newInstance(), R.id.dataLayout)
                    true
                }else -> false
            }
        }
    }

    fun startFragment(f: Fragment, idHolder: Int){
        supportFragmentManager
            .beginTransaction()
            .replace(idHolder, f)
            .commit()
    }

}


