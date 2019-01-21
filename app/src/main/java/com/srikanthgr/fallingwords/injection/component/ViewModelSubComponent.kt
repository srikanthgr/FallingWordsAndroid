package com.srikanthgr.fallingwords.injection.component


import com.srikanthgr.fallingwords.presentation.fallingwords.FallingWordsModel
import dagger.Subcomponent

@Subcomponent
interface ViewModelSubComponent {
    @Subcomponent.Builder
    interface Builder {
        fun build(): ViewModelSubComponent
    }

    fun fallingWordsModel(): FallingWordsModel
}
