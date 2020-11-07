package com.cestrada.hilttest.ui

import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import com.cestrada.hilttest.model.Blog
import com.cestrada.hilttest.repository.BlogRepository
import com.cestrada.hilttest.utils.DataState
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

class MainViewModel
@ViewModelInject constructor(
    private val blogsRepository: BlogRepository,
    @Assisted private val savedStateHandle: SavedStateHandle)
    : ViewModel()  {

    private  val _dataState: MutableLiveData<DataState<List<Blog>>> = MutableLiveData()

    val dataState: LiveData<DataState<List<Blog>>>
        get() = _dataState

    fun setStateEvent(mainStateEvent: MainStateEvent){
        viewModelScope.launch {
            when(mainStateEvent){
                is MainStateEvent.GetBlogEvents -> {
                    blogsRepository.getBlogs()
                        .onEach {dataState ->
                            _dataState.value = dataState
                        }
                        .launchIn(viewModelScope)
                }
            }
        }
    }

}

sealed class MainStateEvent {
    object  GetBlogEvents: MainStateEvent()
    object None: MainStateEvent()
}