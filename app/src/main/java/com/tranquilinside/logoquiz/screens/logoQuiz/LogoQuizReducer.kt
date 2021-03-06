package com.tranquilinside.logoquiz.screens.logoQuiz

import com.tranquilinside.logoquiz.Action
import com.tranquilinside.logoquiz.Event
import com.tranquilinside.logoquiz.Reducer
import com.tranquilinside.logoquiz.State
import io.reactivex.Observable

class LogoQuizReducer: Reducer {
    override fun reduce(state: State, event: Event): State {
        return when (event) {
            is Event.LoadRandomQuizData -> state.copy(actions = Observable.just(Action.ShowCurrentScreen, Action.GetRandomQuizData))
            is Event.UpdateCurrentQuizData -> state.copy(currentQuiz = event.currentQuizData)
        }
    }


}