package com.intern.postinall.data

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import retrofit2.HttpException
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PostRepository @Inject constructor(
    private val apiService: ApiService
) {
    private val _posts = MutableLiveData<List<PostsItem>>()
    val posts: LiveData<List<PostsItem>> = _posts

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    private val _detailPost = MutableLiveData<PostsItem>()
    val detailPost: LiveData<PostsItem> = _detailPost

    private val _toastMessage = MutableLiveData<String>()
    val toastMessage: LiveData<String> = _toastMessage

    suspend fun getPosts() {
        _isLoading.value = true
        try {
            val response = apiService.getPost()
            if (response.isSuccessful) {
                _isLoading.value = false
                val responseBody = response.body()
                _posts.value = responseBody?.posts
            } else {
                _isLoading.value = false
                _toastMessage.value = response.message()
            }
        } catch (e: HttpException) {
            _isLoading.value = false
            _toastMessage.value = e.message
            e.message?.let { Log.d("bug http", it) }
        } catch (e: Exception) {
            _isLoading.value = false
            _toastMessage.value = e.message
            e.message?.let { Log.d("bug exception", it) }
        }
    }

    suspend fun getDetailPost(id: Int) {
        _isLoading.value = true
        try {
            val response = apiService.getDetailPost(id)
            if (response.isSuccessful) {
                _isLoading.value = false
                _detailPost.value = response.body()
            } else {
                _isLoading.value = false
                _toastMessage.value = response.message()
            }
        } catch (e: HttpException) {
            _isLoading.value = false
            _toastMessage.value = e.message
        } catch (e: Exception) {
            _isLoading.value = false
            _toastMessage.value = e.message
        }
    }
}