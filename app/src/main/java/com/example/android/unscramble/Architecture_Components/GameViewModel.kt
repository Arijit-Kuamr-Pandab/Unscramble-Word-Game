package com.example.android.unscramble.Architecture_Components

import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.android.unscramble.ui.game.MAX_NO_OF_WORDS
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
        if(currentWord.equals(tempWord)){
            tempWord.shuffle()
        }
        if (wordList.contains(currentWord)){
            getNextWord()
        } else {
            _currentScrambledWord = String(tempWord)
            _currentWordCount++
            wordList.add(currentWord)
        }

    }

    fun nextWord(): Boolean{
        return if (currentWordCount< MAX_NO_OF_WORDS){
            getNextWord()
            true
        } else {
            false
        }
    }

    fun updateWordCount():Int{
        return _currentWordCount++
    }

    fun updateScore():Int{
        return _score++
    }

}