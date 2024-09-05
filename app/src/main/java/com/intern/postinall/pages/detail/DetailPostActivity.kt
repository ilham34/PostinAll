package com.intern.postinall.pages.detail

import android.opengl.Visibility
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.intern.postinall.databinding.ActivityDetailPostBinding
import com.intern.postinall.utils.Helper
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailPostActivity : AppCompatActivity() {
    lateinit var binding: ActivityDetailPostBinding
    private val detailViewModel: DetailPostViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailPostBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        showDetail()
        helper()
    }

    private fun showDetail() {
        val id = intent.getIntExtra(EXTRA_ID, 0)

        detailViewModel.getDetailPost(id)
        detailViewModel.detailPost.observe(this) {
            binding.apply {
                tvTitle.text = it.title
                tvBody.text = it.body
                tvLikes.text = it.reactions.likes.toString()
                tvDislikes.text = it.reactions.dislikes.toString()
                tvViews.text = it.views.toString()
                tvTags.text = it.tags.joinToString(", ")
            }
        }
    }

    private fun helper() {
        detailViewModel.isLoading.observe(this) {
            Helper.showLoading(it, binding.progressbar)
            if (it != true) {
                binding.penutup.visibility = View.GONE
            }

        }
        detailViewModel.toastMsg.observe(this) {
            Helper.showText(this, it)
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return true
    }

    companion object {
        const val EXTRA_ID = "extra_id"
    }
}