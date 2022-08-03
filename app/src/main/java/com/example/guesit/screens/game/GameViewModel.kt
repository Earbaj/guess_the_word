package com.example.guesit.screens.game

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class GameViewModel: ViewModel (){
    // The current word
    private var _word = MutableLiveData<String>()
    var word: LiveData<String>
        get() {
            return _word
        }
        set(value) {}

    // The current score
    private var _score = MutableLiveData<Int>()
    var score: LiveData<Int>
        get() {
            return _score
        }
        set(value) {}

    private var _gameHasFinishe = MutableLiveData<Boolean>()
    var gameHasFinishe: LiveData<Boolean>
        get() {
            return _gameHasFinishe
        }
        set(value) {}

    // The list of words - the front of the list is the next word to guess
    lateinit var wordList: MutableList<String>

    init {
        resetList()
        _score.value = 0
        _word.value = wordList[0]
        _gameHasFinishe.value = false
        Log.i("GameView","GameViewModel created!")
    }
    /**
     * Resets the list of words and randomizes the order
     */
    fun resetList() {
        wordList = mutableListOf(
            "queen",
            "hospital",
            "basketball",
            "cat",
            "change",
            "snail",
            "soup",
            "calendar",
            "sad",
            "desk",
            "guitar",
            "home",
            "railway",
            "zebra",
            "jelly",
            "car",
            "crow",
            "trade",
            "bag",
            "roll",
            "bubble"
        )
        wordList.shuffle()
    }
    /**
     * Moves to the next word in the list
     */
    private fun nextWord() {
        //Select and remove a word from the list
        if (wordList.isEmpty()) {
            _gameHasFinishe.value = true
        } else {
            _word.value = wordList.removeAt(0)
        }
    }

    /** Methods for buttons presses **/

    fun onSkip() {
        _score.value = _score.value?.minus(1)
        nextWord()
    }

    fun onCorrect() {
        _score.value = _score.value?.plus(1)
        nextWord()
    }
    override fun onCleared() {
        super.onCleared()
        _gameHasFinishe.value = false
        Log.i("GameView","GameViewModel destroyed!")
    }

    fun gamefinish(){
        _gameHasFinishe.value = false
    }
}