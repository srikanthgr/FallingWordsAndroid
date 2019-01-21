package com.srikanthgr.fallingwords.injection.component

import com.srikanthgr.fallingwords.FallingWordsApplication
import com.srikanthgr.fallingwords.data.repository.fallingwords.FallingWordsRepository
import com.srikanthgr.fallingwords.injection.module.ApplicationModule
import com.srikanthgr.fallingwords.utils.ProjectViewModelFactory
import dagger.Component
import javax.inject.Singleton


/**
 * Application component which is used by the injection which utilizes {@link Application Module} and {@link FallingWordsModule}.
 */
@Singleton
@Component(modules = [ApplicationModule::class, FallingWordsModule::class])
interface ApplicationComponent {

    fun application(): FallingWordsApplication

    fun fallingWordRepository(): FallingWordsRepository

    fun viewModelFactory(): ProjectViewModelFactory
}
