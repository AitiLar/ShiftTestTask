package com.shifttest.shifttestapp.model

data class BinListData(
    val number: NumberData,
    val scheme: String,
    val type: String,
    val brand: String,
    val prepaid: Boolean,
    val country: CountryData,
    val bank: BankData
    )


data class NumberData(val length: Int, val luhn: Boolean)

data class CountryData(val numeric: String,
                       val alpha2: String,
                       val name: String,
                       val emoji: String,
                       val currency: String,
                       val latitude: Int,
                       val longitude: Int)

data class BankData(val name: String,
                    val url: String,
                    val phone: String,
                    val city: String)