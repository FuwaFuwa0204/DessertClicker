package com.example.dessertclicker.ui

import com.example.dessertclicker.data.Datasource

data class DessertUiState(
    val revenue:Int = 0,
    val dessertsSold:Int = 0,
    val currentDessertIndex: Int=Datasource.dessertList[0].startProductionAmount,
    val currentDessertPrice: Int= Datasource.dessertList[0].price,
    val currentDessertImageId:Int= Datasource.dessertList[0].imageId
)
