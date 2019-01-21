package com.srikanthgr.fallingwords

import android.app.Application
import com.srikanthgr.fallingwords.injection.component.ApplicationComponent
import com.srikanthgr.fallingwords.injection.component.DaggerApplicationComponent
import com.srikanthgr.fallingwords.injection.module.ApplicationModule


class FallingWordsApplication : Application() {

    var applicationComponent: ApplicationComponent? = null
        private set

    override fun onCreate() {
        super.onCreate()
        initializeInjector()
    }

    private fun initializeInjector() {
        applicationComponent = DaggerApplicationComponent.builder()
            .applicationModule(ApplicationModule(this))
            .build()
    }
}