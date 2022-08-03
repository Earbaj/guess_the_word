package com.example.guesit.screens.score

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ScoreViewModel(finalScore : Int) : ViewModel() {
    private var _score = MutableLiveData<Int>()

    var score : LiveData<Int>
        get() {
            return _score
        }
        set(value) {}
    init {
        _score.value = finalScore
    }
}