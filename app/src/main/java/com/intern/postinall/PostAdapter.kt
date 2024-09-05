package com.intern.postinall

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.intern.postinall.data.PostsItem
import com.intern.postinall.databinding.ItemPostBinding
import com.intern.postinall.utils.PostsDiffCallback

class PostAdapter(
    private val onItemClicked: (Int) -> Unit
) : RecyclerView.Adapter<PostAdapter.ListViewHolder>() {
    private var listPosts = ArrayList<PostsItem>()

    fun setData(newListData: List<PostsItem>?) {
        if (newListData == null) return

        val diffCallback = PostsDiffCallback(listPosts, newListData)
        val diffResult = DiffUtil.calculateDiff(diffCallback)
        listPosts.clear()
        listPosts.addAll(newListData)
        diffResult.dispatchUpdatesTo(this)
    }

    inner class ListViewHolder(private var binding: ItemPostBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(data: PostsItem) {
            binding.apply {
                tvTitle.text = data.title
                tvBody.text = data.body
                tvViews.text = data.views.toString()
                tvLikes.text = data.reactions.likes.toString()
                itemView.setOnClickListener {
                    onItemClicked(data.id)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val binding = ItemPostBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ListViewHolder(binding)
    }

    override fun getItemCount(): Int = listPosts.size

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val post = listPosts[position]
        holder.bind(post)
    }
}