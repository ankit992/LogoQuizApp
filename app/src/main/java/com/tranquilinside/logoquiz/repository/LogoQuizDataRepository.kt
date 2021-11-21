package com.tranquilinside.logoquiz.repository

import com.tranquilinside.logoquiz.entities.LogoQuizItem

interface LogoQuizDataRepository {
    fun getRandomLogoQuizData(): LogoQuizItem?
}