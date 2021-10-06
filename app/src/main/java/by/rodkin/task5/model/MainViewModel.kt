package by.rodkin.task5.model

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import by.rodkin.task5.PagingSource
import by.rodkin.task5.retrofit.RetrofitService

class MainViewModel : ViewModel() {

    val flow =
        Pager(PagingConfig(10)) {
            PagingSource(RetrofitService.create())
        }.flow.cachedIn(viewModelScope)
}