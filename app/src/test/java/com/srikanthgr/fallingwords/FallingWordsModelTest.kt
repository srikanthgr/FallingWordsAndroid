package com.srikanthgr.fallingwords

import android.util.Log
import com.srikanthgr.fallingwords.data.entity.FallingWords
import com.srikanthgr.fallingwords.data.entity.FallingWordsResponse
import com.srikanthgr.fallingwords.data.repository.fallingwords.FallingWordsRepository
import com.srikanthgr.fallingwords.data.repository.fallingwords.FallingWordsRepositoryImpl
import com.srikanthgr.fallingwords.interactor.usecase.FallingWordsUseCase
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.ArgumentMatchers.any
import org.mockito.ArgumentMatchers.anyString
import org.mockito.junit.MockitoJUnit
import org.powermock.api.mockito.PowerMockito
import org.robolectric.RobolectricTestRunner
import kotlin.coroutines.Continuation

@RunWith(RobolectricTestRunner::class)
class FallingWordsModelTest {

    @get:Rule
    var rule = MockitoJUnit.rule()

    lateinit var usecase: FallingWordsUseCase

    lateinit var wordsRepositoryMock: FallingWordsRepository


    @Before
    fun setUp() {
        wordsRepositoryMock = FallingWordsRepositoryImpl(FallingWordsApplication())
        usecase = FallingWordsUseCase(wordsRepositoryMock)

    }

    private fun runTestBlocking(block: suspend () -> Unit) {
        val thread = Thread {
            runBlocking {
                block.invoke()
            }
        }
        thread.join()
    }

    @Test
    fun executeOnBackground_query() {
        runTestBlocking {
            val translation = FallingWords("sampleword", "translatedWord", true)
            val translationResponse = FallingWordsResponse(translation)

            PowerMockito.doReturn(translationResponse)
                .`when`(wordsRepositoryMock, anyString(), anyString(), any(Continuation::class.java))
            val result = usecase.executeOnBackground()
            Log.d("FallingWordModelTest", result.toString())
            Assert.assertNotNull(result)
            Assert.assertEquals("sampleword", result.fallingWords.challengeWord)
            Assert.assertEquals("translatedWord", result.fallingWords.translatedWord)
            Assert.assertEquals(false, result.fallingWords.isCorrect)
        }
    }
}
