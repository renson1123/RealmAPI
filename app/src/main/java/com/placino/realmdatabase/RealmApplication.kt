package com.placino.realmdatabase

import android.app.Application
import io.realm.Realm
import io.realm.RealmConfiguration

class RealmApplication : Application() {
    private var config : RealmConfiguration? = null

    override fun onCreate() {
        super.onCreate()

        Realm.init(this)
        config = RealmConfiguration.Builder().name("article.db").deleteRealmIfMigrationNeeded()
            .schemaVersion(0).allowWritesOnUiThread(true).allowWritesOnUiThread(true).build()
        config.let { Realm.setDefaultConfiguration(it) }
    }
}