package com.srikanthgr.fallingwords.data.repository.fallingwords

import com.srikanthgr.fallingwords.data.entity.FallingWordsResponse

/**
 * Repository implementation of the falling words. which will implemented by {@link FallingWordsRepositoryImpl}
 */
interface FallingWordsRepository {
    fun searchWords(): FallingWordsResponse
}