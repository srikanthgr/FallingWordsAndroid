package com.srikanthgr.fallingwords.data.repository.fallingwords

import com.srikanthgr.fallingwords.FallingWordsApplication
import com.srikanthgr.fallingwords.data.cache.DataStore
import com.srikanthgr.fallingwords.data.entity.FallingWordsResponse
import org.json.JSONArray

/**
 * Repository implementation of the falling words.
 * @param fallingWordsApplication  context which will be used by the cache layer to retrieve the data.
 */
class FallingWordsRepositoryImpl(private var fallingWordsApplication: FallingWordsApplication) :
    FallingWordsRepository {

    private var jsonArray: JSONArray? = null

    override fun searchWords(): FallingWordsResponse {
        return jsonArray.let { DataStore.nextTranslation(fallingWordsApplication)}
    }
}