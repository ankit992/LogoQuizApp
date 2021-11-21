package com.tranquilinside.logoquiz


import com.tranquilinside.logoquiz.screens.logoQuiz.LogoQuizScreen
import io.reactivex.Observable

data class State(
    val currentScreen:Screen = LogoQuizScreen(),
    val logoQuizScreen: LogoQuizScreen = LogoQuizScreen(),
    val actions: Observable<Action> = Observable.fromIterable(emptyList<Action>()))
