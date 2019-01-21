package com.srikanthgr.fallingwords.data.entity

/**
 * Model class which represents the dat dictionary.
 * @param challengeWord the original word.
 * @param translatedWord the translated version of the word.
 * @param isCorrect validate the challenged and the translated word.
 */
data class FallingWords(val challengeWord: String, val translatedWord: String, val isCorrect: Boolean)
