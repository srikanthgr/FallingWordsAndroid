package com.srikanthgr.fallingwords.presentation.fallingwords

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.srikanthgr.fallingwords.data.entity.FallingWordsResponse
import com.srikanthgr.fallingwords.interactor.usecase.FallingWordsUseCase
import com.srikanthgr.fallingwords.presentation.model.FallingWords
import javax.inject.Inject


/**
 * Viewmodel class implmentation of falling words.
 * @param fallingWordsUseCase the corresponding interactor class which will be invoked from the presentation layer.
 * return the response.
 */
class FallingWordsModel @Inject
constructor(private val fallingWordsUseCase: FallingWordsUseCase) : ViewModel() {
    val result = MutableLiveData<FallingWords>()

    override fun onCleared() {
        super.onCleared()
        fallingWordsUseCase.unsubscribe()
    }

    /**
     * Actual implementation of the usecase will be invoked.
     * wrap the response to a {@link FallingWords} to be used by the presentation layer.
     */
    fun fallingWords() {
        fallingWordsUseCase.execute {
            onComplete { wordTranslationResponse: FallingWordsResponse ->
                result.value = FallingWords(
                    wordTranslationResponse.fallingWords.translatedWord,
                    wordTranslationResponse.fallingWords.challengeWord,
                    wordTranslationResponse.fallingWords.isCorrect
                )
            }
        }
    }
}
