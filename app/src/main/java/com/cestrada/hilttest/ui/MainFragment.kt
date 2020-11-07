package com.cestrada.hilttest.ui

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.cestrada.hilttest.R
import com.cestrada.hilttest.model.Blog
import com.cestrada.hilttest.utils.DataState
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_main.*
import java.lang.StringBuilder

@AndroidEntryPoint
class MainFragment: Fragment(R.layout.fragment_main) {

    private val viewModel: MainViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        subscribeObservers()
        viewModel.setStateEvent(MainStateEvent.GetBlogEvents)
    }

    private fun subscribeObservers(){
        viewModel.dataState.observe(viewLifecycleOwner, Observer { dataState ->
            when(dataState){
                is DataState.Success<List<Blog>> -> {
                    displayProgressBar(false)
                    displayTitles(dataState.data)
                }
                is DataState.Error -> {
                    displayProgressBar(false)
                    displayError(dataState.exception.message)
                }
                is DataState.Loading -> {
                    displayProgressBar(true)
                }
            }
        })
    }

    private fun displayError(message: String?) {
        text.text = message ?: "Unknown Error"
    }

    private fun displayProgressBar(isDisplayed: Boolean){
        progress_circular.visibility = if(isDisplayed) View.VISIBLE else View.GONE
    }

    private fun displayTitles(blogs: List<Blog>) {
        val sb = StringBuilder()
        for (blog in blogs){
            sb.append(blog.title + "\n");
        }

        text.text = sb
    }
}