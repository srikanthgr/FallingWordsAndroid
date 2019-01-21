package com.srikanthgr.fallingwords.injection.module

import com.srikanthgr.fallingwords.FallingWordsApplication
import com.srikanthgr.fallingwords.injection.component.ViewModelSubComponent
import com.srikanthgr.fallingwords.utils.ProjectViewModelFactory
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module(subcomponents = [ViewModelSubComponent::class])
class ApplicationModule(private val application: FallingWordsApplication) {

    @Provides
    @Singleton
    internal fun provideApplication(): FallingWordsApplication {
        return application
    }


    @Singleton
    @Provides
    internal fun provideViewModelFactory(
        viewModelSubComponent: ViewModelSubComponent.Builder
    ): ProjectViewModelFactory {
        return ProjectViewModelFactory(viewModelSubComponent.build())
    }
}
