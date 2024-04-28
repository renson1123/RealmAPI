package com.placino.realmdatabase

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView

class ArticleAdapter : ListAdapter<ArticleModel, ArticleHolder>(MyDiffUtil) {
    object MyDiffUtil : DiffUtil.ItemCallback<ArticleModel>(){
        override fun areItemsTheSame(oldItem: ArticleModel, newItem: ArticleModel): Boolean {
            return  oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: ArticleModel, newItem: ArticleModel): Boolean {
            return oldItem.id == newItem.id
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticleHolder {
        return ArticleHolder.create(parent)
    }

    override fun onBindViewHolder(holder: ArticleHolder, position: Int) {
        holder.bindView(getItem(position))
    }
}

class ArticleHolder(private val view: View) : RecyclerView.ViewHolder(view){
    private lateinit var tvTitle: AppCompatTextView
    private lateinit var tvDes: AppCompatTextView

    fun bindView(article: ArticleModel) {
        tvTitle = view.findViewById(R.id.tv_title)
        tvDes = view.findViewById(R.id.tv_des)

        tvTitle.text = article.title
        tvDes.text = article.title
    }

    companion object{
        val LAYOUT = R.layout.layout_article_viewholder
        fun create(parent: ViewGroup) = ArticleHolder(
            LayoutInflater.from(parent.context).inflate(LAYOUT, parent, false)
        )
    }
}