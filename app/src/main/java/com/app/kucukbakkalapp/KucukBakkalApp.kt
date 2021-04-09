package com.app.kucukbakkalapp

import androidx.multidex.MultiDexApplication

class KucukBakkalApp : MultiDexApplication() {

    init {
        if (appInstance == null) {
            appInstance = this
        }
    }

    companion object {
        private var appInstance: KucukBakkalApp? = null
        fun appInstance(): KucukBakkalApp? {
            return appInstance
        }
    }

    override fun onCreate() {
        super.onCreate()
        if (appInstance == null) {
            appInstance = this
        }
    }
}
