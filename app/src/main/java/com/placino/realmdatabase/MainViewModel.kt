package com.placino.realmdatabase

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.realm.Realm
import io.realm.kotlin.deleteFromRealm
import java.util.UUID

class MainViewModel : ViewModel(){
    private var realm = Realm.getDefaultInstance()
    val allArticle by lazy { MutableLiveData<List<ArticleModel>>() }

    fun addArticle(title: String, des: String){
        realm.executeTransaction {
            val article = it.createObject(ArticleModel::class.java, UUID.randomUUID().toString())
            article.title = title
            article.description = des
            realm.insertOrUpdate(article)
        }
    }

    fun getAllArticles(){
        val articles = realm.where(ArticleModel::class.java).findAll()
        allArticle.value = realm.copyFromRealm(articles)
    }

    fun deleteArticle(id: String){
        val article = realm.where(ArticleModel::class.java).equalTo("id", id).findFirst()
        realm.executeTransaction{
            article?.deleteFromRealm()
        }
    }
}