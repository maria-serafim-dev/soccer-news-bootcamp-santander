package com.example.soccernews.ui.adapter

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.soccernews.databinding.NewsItemBinding
import com.example.soccernews.domain.News

class NewsAdapter (private val listNews: List<News>) : RecyclerView.Adapter<NewsAdapter.ViewHolder>() {

    private lateinit var binding: NewsItemBinding


    class ViewHolder (private val binding: NewsItemBinding) : RecyclerView.ViewHolder (binding.root) {
        fun onBind(news: News, context: Context){
            binding.tvTitle.text = news.title
            binding.tvDescription.text = news.description
            Glide.with(context).load(news.image).into(binding.ivThumbnail)

            binding.btOpenLink.setOnClickListener {
                val queryUrl: Uri = Uri.parse(news.link)
                val intent = Intent(Intent.ACTION_VIEW, queryUrl)
                context.startActivity(intent)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        binding = NewsItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val news = listNews[position]
        holder.onBind(news, holder.itemView.context)
    }

    override fun getItemCount(): Int {
        return listNews.size
    }
}