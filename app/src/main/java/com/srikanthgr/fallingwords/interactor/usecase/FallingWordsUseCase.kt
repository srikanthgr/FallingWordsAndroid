package com.srikanthgr.fallingwords.interactor.usecase

import com.srikanthgr.fallingwords.data.entity.FallingWordsResponse
import com.srikanthgr.fallingwords.data.repository.fallingwords.FallingWordsRepository
import com.srikanthgr.fallingwords.interactor.UseCase
import javax.inject.Inject

/**
 * FallingWordsUseCase implementation.
 * @param fallingWordsRepository implementation of the fallingwords.
 */
class FallingWordsUseCase @Inject constructor(private val fallingWordsRepository: FallingWordsRepository) :
    UseCase<FallingWordsResponse>() {

    public override suspend fun executeOnBackground(): FallingWordsResponse {
        return fallingWordsRepository.searchWords()
    }
}