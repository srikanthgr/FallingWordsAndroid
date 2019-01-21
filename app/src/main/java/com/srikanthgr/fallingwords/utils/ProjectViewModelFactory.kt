package com.srikanthgr.fallingwords.utils

import androidx.collection.ArrayMap
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.srikanthgr.fallingwords.injection.component.ViewModelSubComponent
import com.srikanthgr.fallingwords.presentation.fallingwords.FallingWordsModel


class ProjectViewModelFactory(viewModelSubComponent: ViewModelSubComponent) : ViewModelProvider.Factory {
    private val creators: ArrayMap<Class<*>, () -> ViewModel> = ArrayMap()

    init {
        creators[FallingWordsModel::class.java] = { viewModelSubComponent.fallingWordsModel() }
    }

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        var creator: (() -> ViewModel)? = creators[modelClass]
        if (creator == null) {
            for ((key, value) in creators) {
                if (modelClass.isAssignableFrom(key)) {
                    creator = value
                    break
                }
            }
        }
        if (creator == null) {
            throw IllegalArgumentException("Unknown model class $modelClass")
        }
        try {
            return creator.invoke() as T
        } catch (e: Exception) {
            throw RuntimeException(e)
        }

    }
}