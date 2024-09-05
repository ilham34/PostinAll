package com.intern.postinall.pages.home

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.intern.postinall.PostAdapter
import com.intern.postinall.databinding.ActivityMainBinding
import com.intern.postinall.pages.detail.DetailPostActivity
import com.intern.postinall.pages.detail.DetailPostActivity.Companion.EXTRA_ID
import com.intern.postinall.utils.Helper
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val homeViewModel: HomeViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        showAllPost()
        helper()
    }

    private fun showAllPost() {
        val postAdapter = PostAdapter {
            val intentA = Intent(this@MainActivity, DetailPostActivity::class.java)
            intentA.putExtra(EXTRA_ID, it)
            startActivity(intentA)
        }
        homeViewModel.getPosts()
        homeViewModel.posts.observe(this) {
            postAdapter.setData(it)
        }
        binding.rvPost.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            setHasFixedSize(true)
            adapter = postAdapter
        }
    }

    private fun helper() {
        homeViewModel.isLoading.observe(this) {
            Helper.showLoading(it, binding.progressbar)
        }
        homeViewModel.toastMsg.observe(this) {
            Helper.showText(this, it)
        }
    }
}