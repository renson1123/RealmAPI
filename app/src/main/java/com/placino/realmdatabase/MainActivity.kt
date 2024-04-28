package com.placino.realmdatabase

import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatEditText
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    private lateinit var edTitle: AppCompatEditText
    private lateinit var edDes: AppCompatEditText
    private lateinit var btnInsert: Button
    private lateinit var btnGet: Button
    private lateinit var rvArticle: RecyclerView

    private lateinit var viewModel: MainViewModel
    private var articleAdapter: ArticleAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        viewModel = ViewModelProvider(this)[MainViewModel::class.java]

        initView()

        viewModel.allArticle.observe(this) {
            articleAdapter?.submitList(it)

        }

        btnInsert.setOnClickListener{ insertArticle() }
        btnGet.setOnClickListener { getArticles() }
    }

    private fun getArticles(){
        viewModel.getAllArticles()
    }

    private fun insertArticle() {
        val title = edTitle.text.toString()
        val des = edDes.text.toString()
        viewModel.addArticle(title, des)
        Toast.makeText(this, "Article added...", Toast.LENGTH_SHORT).show()
    }

    private fun initView(){
        edTitle = findViewById(R.id.ed_title)
        edDes = findViewById(R.id.ed_des)
        btnInsert = findViewById(R.id.btn_insert)
        btnGet = findViewById(R.id.btn_get)
        rvArticle = findViewById(R.id.rv_article)

        rvArticle.layoutManager = LinearLayoutManager(this)
        articleAdapter = ArticleAdapter()
        rvArticle.adapter = articleAdapter

    }
}