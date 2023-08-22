package com.example.newsappuser

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bumptech.glide.Glide
import com.example.newsappuser.databinding.NewsitemBinding

class newslistAdapter : Adapter<newslistAdapter.newsHolder>() {

    var newslist = ArrayList<newslistmodel>()
    lateinit var context: Context

    class newsHolder(itemView: NewsitemBinding) : ViewHolder(itemView.root) {
        var binding = itemView
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): newsHolder {
        context = parent.context
        var binding = NewsitemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return newsHolder(binding)
    }

    override fun getItemCount(): Int {
        return newslist.size
    }

    override fun onBindViewHolder(holder: newsHolder, position: Int) {
        holder.binding.apply {
            newslist.get(position).apply {
                txttitle.text = title
                txttext.text = text
                Glide.with(context).load(img).into(imgpost)

            }
        }
    }

    fun update(newslist: ArrayList<newslistmodel>) {
        this.newslist = newslist
        notifyDataSetChanged()


    }

    fun read(newslist: ArrayList<newslistmodel>) {

    }
}