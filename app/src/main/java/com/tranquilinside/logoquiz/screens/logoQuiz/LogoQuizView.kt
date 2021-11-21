package com.tranquilinside.logoquiz.screens.logoQuiz

import android.content.Context
import android.os.Handler
import android.util.AttributeSet
import android.view.ViewGroup
import android.widget.LinearLayout
import com.tranquilinside.logoquiz.R
import com.tranquilinside.logoquiz.entities.LogoQuizItem
import io.reactivex.Observable
import io.reactivex.subjects.PublishSubject

class LogoQuizView: LinearLayout, LogoQuizViewInterface {

    constructor(context: Context) : super(context)
    constructor(context: Context, attributes: AttributeSet?) : super(context, attributes)

    private val events: PublishSubject<LogoQuizViewEvents> = PublishSubject.create()

    fun eventsObservable(): Observable<LogoQuizViewEvents> = events.hide().share()

    fun updateRandomQuizData(randomQuizData: LogoQuizItem) {
        //to set image url
    }


    init {
        this.layoutParams = LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.MATCH_PARENT
        )
        android.view.LayoutInflater.from(context).inflate(R.layout.view_logo_quiz, this, true)
        Handler().postDelayed(
            { events.onNext(LogoQuizViewEvents.GetRandomQuizData) }, 200
        )
    }

    override fun setJumbledAlphabetList(jumbledAlphabetList: List<Char>) {
        // to set jumbled characters for rv_keyboard here
    }
}