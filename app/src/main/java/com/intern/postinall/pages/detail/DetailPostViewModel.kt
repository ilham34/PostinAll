package com.intern.postinall.pages.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.intern.postinall.data.PostRepository
import com.intern.postinall.data.PostsItem
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailPostViewModel @Inject constructor(private val repository: PostRepository) : ViewModel() {
    val detailPost: LiveData<PostsItem> = repository.detailPost
    val isLoading: LiveData<Boolean> = repository.isLoading
    val toastMsg: LiveData<String> = repository.toastMessage

    fun getDetailPost(id: Int) {
        viewModelScope.launch {
            repository.getDetailPost(id)
        }
    }
}