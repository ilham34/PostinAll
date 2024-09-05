package com.intern.postinall.pages.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.intern.postinall.data.PostRepository
import com.intern.postinall.data.PostsItem
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val repository: PostRepository) : ViewModel() {
    val posts: LiveData<List<PostsItem>> = repository.posts
    val isLoading: LiveData<Boolean> = repository.isLoading
    val toastMsg: LiveData<String> = repository.toastMessage

    fun getPosts() {
        viewModelScope.launch {
            repository.getPosts()
        }
    }
}