package com.placino.realmdatabase

import androidx.lifecycle.ViewModel
import io.realm.Realm
import java.util.UUID

class MainViewModel : ViewModel(){
    private var realm = Realm.getDefaultInstance()

    fun addArticle(title: String, des: String){
        realm.executeTransaction {
            val article = it.createObject(ArticleModel::class.java, UUID.randomUUID().toString())
            article.title = title
            article.description = des
            realm.insertOrUpdate(article)
        }
    }
}