package com.placino.realmdatabase

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView

class ArticleAdapter : ListAdapter<ArticleModel, ArticleHolder>(MyDiffUtil) {
    private var onActionClick: OnActionClick? = null

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
        holder.bindView(getItem(position), onActionClick)
    }

    fun setOnActionClickListener(onActionClick: OnActionClick){
        this.onActionClick = onActionClick
    }
}

class ArticleHolder(private val view: View) : RecyclerView.ViewHolder(view){
    private lateinit var tvTitle: AppCompatTextView
    private lateinit var tvDes: AppCompatTextView
    private lateinit var ivDelete: AppCompatImageView

    fun bindView(article: ArticleModel, onActionClick: OnActionClick?) {
        tvTitle = view.findViewById(R.id.tv_title)
        tvDes = view.findViewById(R.id.ed_des)
        ivDelete = view.findViewById(R.id.iv_delete)

        tvTitle.text = article.title
        tvDes.text = article.title

        ivDelete.setOnClickListener{
            onActionClick?.onClickDelete(it, article)
        }
    }

    companion object{
        val LAYOUT = R.layout.layout_article_viewholder
        fun create(parent: ViewGroup) = ArticleHolder(
            LayoutInflater.from(parent.context).inflate(LAYOUT, parent, false)
        )
    }
}

interface OnActionClick{
    fun onClickDelete(view: View, article: ArticleModel)
}