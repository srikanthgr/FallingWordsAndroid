package com.srikanthgr.fallingwords.data.cache

import com.srikanthgr.fallingwords.FallingWordsApplication
import com.srikanthgr.fallingwords.data.entity.FallingWordsResponse
import com.srikanthgr.fallingwords.data.entity.FallingWords
import org.json.JSONArray
import org.json.JSONObject
import java.util.*

/**
 * Cache layer mechanism to return the data from the assets folder in the cache layer.
 */
class DataStore {

    companion object {

        /**
         * Find the next translation to validate on the presentation layers.
         * @param application context required for retrieving the data from the assets folder.
         */
        fun nextTranslation(application: FallingWordsApplication): FallingWordsResponse {
            val dataDictionary: JSONArray =
                returnJsonArray(application)
            dataDictionary.let {
                val translationIndexes =
                    getNextTranslationIndexes(
                        dataDictionary,
                        random = Random()
                    )

                val isCorrect = translationIndexes[0] == translationIndexes[1]
                return FallingWordsResponse(
                    FallingWords(
                        (dataDictionary.get(translationIndexes[1]) as JSONObject).getString(
                            "text_spa"
                        ), (dataDictionary.get(translationIndexes[0]) as JSONObject).getString("text_eng"), isCorrect
                    )
                )
            }
        }

        /**
         * Method which returns the index of the originalword and the translated text.
         * based on the index the original and translated will ev validated. considering a random factor of 3.
         * @param dataDictionary jsonArray
         */
        private fun getNextTranslationIndexes(dataDictionary: JSONArray, random: Random): IntArray {
            val upperIndex = dataDictionary.length()
            var randomWordIndex1 = random.nextInt(upperIndex)
            val randomWordIndex2 = random.nextInt(upperIndex)
            val randomFactor = 3
            if (random.nextInt(randomFactor) == 0) {
                randomWordIndex1 = randomWordIndex2
            }

            return intArrayOf(randomWordIndex1, randomWordIndex2)
        }

        /**
         * Method which returns the json array from the asset folder.
         * @param application context for the resources.
         */
        private fun returnJsonArray(application: FallingWordsApplication): JSONArray {
            return JSONArray(application.assets.open("words.json").bufferedReader().use { it.readText() })
        }
    }

}