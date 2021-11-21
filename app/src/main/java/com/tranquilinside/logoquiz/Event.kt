package com.tranquilinside.logoquiz

import com.tranquilinside.logoquiz.entities.LogoQuizItem

sealed class Event {
    data class UpdateCurrentQuizData(val currentQuizData: LogoQuizItem) : Event()
    object LoadRandomQuizData: Event()
}
