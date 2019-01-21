package com.srikanthgr.fallingwords.injection.component

import com.srikanthgr.fallingwords.FallingWordsApplication
import com.srikanthgr.fallingwords.data.repository.fallingwords.FallingWordsRepository
import com.srikanthgr.fallingwords.data.repository.fallingwords.FallingWordsRepositoryImpl

import dagger.Module
import dagger.Provides

/**
 * Module which provides repository implementation of the Fallingwords implementation.
 */
@Module
class FallingWordsModule {

    @Provides
    internal fun provideWordsRepository(application: FallingWordsApplication): FallingWordsRepository {
        return FallingWordsRepositoryImpl(application)
    }
}
