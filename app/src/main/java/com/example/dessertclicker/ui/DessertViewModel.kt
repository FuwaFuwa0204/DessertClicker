package com.example.dessertclicker.ui

import androidx.lifecycle.ViewModel
import com.example.dessertclicker.data.Datasource
import com.example.dessertclicker.data.Datasource.dessertList
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class DessertViewModel: ViewModel() {
    //state로 설정한 변수들을 stateflow로 지정
    private val _uiState = MutableStateFlow(DessertUiState())

    //읽기전용 state
    val uiState: StateFlow<DessertUiState> = _uiState.asStateFlow()

    fun DessertonClick(){
        /*
                        // Update the revenue
                revenue += currentDessertPrice
                dessertsSold++

                // Show the next dessert
                val dessertToShow = determineDessertToShow(desserts, dessertsSold)
                currentDessertImageId = dessertToShow.imageId
                currentDessertPrice = dessertToShow.price
         */
        //매개변수 자동으로 DessertUiState
        _uiState.update {currentState->

            val dessertsSold = currentState.dessertsSold+1
            val nextIndex = ChangeDessertImage(dessertsSold)
/*
    var revenue by rememberSaveable { mutableStateOf(0) }
    var dessertsSold by rememberSaveable { mutableStateOf(0) }

    val currentDessertIndex by rememberSaveable { mutableStateOf(0) }

    var currentDessertPrice by rememberSaveable {
        mutableStateOf(desserts[currentDessertIndex].price)
    }
    var currentDessertImageId by rememberSaveable {
        mutableStateOf(desserts[currentDessertIndex].imageId)
    }
 */
            currentState.copy(
            revenue = currentState.revenue+currentState.currentDessertPrice,
            dessertsSold = dessertsSold,
            currentDessertIndex = nextIndex,
            currentDessertPrice = dessertList[nextIndex].price,
            currentDessertImageId = dessertList[nextIndex].imageId

            )

        }
    }
    //리턴값 지정
    fun ChangeDessertImage(dessertsSold:Int):Int {

        var currentIndex = 0
        //리스트의 인덱스 indices
        for (i in dessertList.indices) {
            if (dessertsSold >= dessertList[currentIndex].startProductionAmount) {
                currentIndex = i
            } else {
                // The list of desserts is sorted by startProductionAmount. As you sell more desserts,
                // you'll start producing more expensive desserts as determined by startProductionAmount
                // We know to break as soon as we see a dessert who's "startProductionAmount" is greater
                // than the amount sold.
                break
            }

        }
        return currentIndex
    }


}