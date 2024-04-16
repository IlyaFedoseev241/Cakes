package com.example.cakes.presentation.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.example.domain.usecase.homeusecase.GetProductListUseCase

class HomeViewModel(private val getProductListUseCase: com.example.domain.usecase.homeusecase.GetProductListUseCase) : ViewModel() {
    val product = liveData {
        emit(getProductListUseCase.execute())
    }
}
