package com.srikanthgr.fallingwords.data.entity


/**
 * Wrap the response from the cache layer which will be used downstream.
 * @param fallingWords {@link Fallingwords}
 */
data class FallingWordsResponse(val fallingWords: FallingWords)