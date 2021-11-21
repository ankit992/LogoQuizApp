package com.tranquilinside.logoquiz

import android.content.Context
import android.view.View
import com.tranquilinside.logoquiz.entities.LogoQuizItem

interface RootView {
    fun getContext(): Context
    fun updateView(view: View)
    fun currentView(): View
    fun showProgressBar()
    fun hideProgressBar()
    fun getRandomQuizData(): LogoQuizItem?
    fun getJumbledAlphabetList(): List<Char>
}
