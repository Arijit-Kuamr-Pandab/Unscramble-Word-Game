package com.example.android.unscramble.Architecture_Components

import android.util.Log
import androidx.lifecycle.ViewModel

class GameViewModel: ViewModel() {

    private var _score = 0
    private var _currentWordCount = 0
    private var _currentScrambledWord = "test"

    val score: Int get() = _score
    val currentWordCount: Int get() = _currentWordCount
    val currentScrambledWord: String get() = _currentScrambledWord

    init {
        Log.d("GameFragment","GameViewModel Created.....init inside ViewModel")
    }

    override fun onCleared() {
        super.onCleared()
        Log.d("GameFragment","GameViewModel Destroyed...onCleared inside ViewModel")
    }

}