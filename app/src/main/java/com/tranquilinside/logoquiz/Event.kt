package com.tranquilinside.logoquiz

sealed class Event {
    object LoadRandomQuizData: Event()
}
