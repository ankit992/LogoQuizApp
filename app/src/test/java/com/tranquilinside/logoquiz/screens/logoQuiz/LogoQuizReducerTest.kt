package com.tranquilinside.logoquiz.screens.logoQuiz

import com.tranquilinside.logoquiz.Action
import com.tranquilinside.logoquiz.Event
import com.tranquilinside.logoquiz.State
import org.junit.Assert
import org.junit.Assert.*
import org.junit.Test

class LogoQuizReducerTest{

    private fun reducer(): LogoQuizReducer = LogoQuizReducer()

    private fun initialState(): State {
        val screens = listOf(LogoQuizScreen())
        return State(
            currentScreen = screens.last(),
            logoQuizScreen = screens.first(),
        )
    }

    @Test
    fun itShouldShowLogoQuizScreenWithShowCurrentScreenAction() {
        val reducer = reducer()
        val nextState = reducer.reduce(initialState(), Event.LoadRandomQuizData)
        assertEquals(nextState.logoQuizScreen, nextState.currentScreen)
        val actionList = nextState.actions.toList()
        assertEquals(2, actionList.blockingGet().count())
        assertTrue(actionList.blockingGet().find { action -> action == Action.ShowCurrentScreen } != null)
    }

}