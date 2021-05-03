package com.example.android.unscramble.Architecture_Components

import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.android.unscramble.ui.game.MAX_NO_OF_WORDS
import com.example.android.unscramble.ui.game.SCORE_INCREASE
import com.example.android.unscramble.ui.game.allWordsList

class GameViewModel: ViewModel() {


    private lateinit var currentWord:String
    private var wordList: MutableList<String> = mutableListOf()
    private var _score = 0
    private var _currentWordCount = 0
    private lateinit var _currentScrambledWord: String

    val score: Int get() = _score
    val currentWordCount: Int get() = _currentWordCount
    val currentScrambledWord: String get() = _currentScrambledWord

    init {
        Log.d("GameFragment","GameViewModel Created!")
        getNextWord()
    }

    override fun onCleared() {
        super.onCleared()
        Log.d("GameFragment","GameViewModel Destroyed...onCleared inside ViewModel")
    }

    fun getNextWord(){
        currentWord = allWordsList.random()
        val tempWord = currentWord.toCharArray()
        tempWord.shuffle()
        while (tempWord.toString().equals(currentWord, false)) {
            tempWord.shuffle()
        }
        if (wordList.contains(currentWord)){
            getNextWord()
        } else {
            _currentScrambledWord = String(tempWord)
            _currentWordCount++
            //_score += SCORE_INCREASE
            wordList.add(currentWord)
        }

    }

    fun nextWord(): Boolean{
        return if (_currentWordCount< MAX_NO_OF_WORDS){
            getNextWord()
            true
        } else {
            false
        }
    }

    private fun increaseScore(){
        _score += SCORE_INCREASE
    }

    fun isUserWordCorrect(playerWord: String): Boolean{
        if (currentWord.equals(playerWord)){
            increaseScore()
            return true
        }
        return false
    }

    fun reInitializedata(){
        _score = 0
        _currentWordCount = 0
        wordList.clear()
        getNextWord()
    }
}