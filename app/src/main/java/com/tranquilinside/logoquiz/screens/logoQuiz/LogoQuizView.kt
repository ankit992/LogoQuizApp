package com.tranquilinside.logoquiz.screens.logoQuiz

import android.content.Context
import android.os.Handler
import android.util.AttributeSet
import android.view.ViewGroup
import android.widget.LinearLayout
import com.tranquilinside.logoquiz.R
import io.reactivex.Observable
import io.reactivex.subjects.PublishSubject

class LogoQuizView: LinearLayout, LogoQuizViewInterface {

    constructor(context: Context) : super(context)
    constructor(context: Context, attributes: AttributeSet?) : super(context, attributes)

    private val events: PublishSubject<LogoQuizViewEvents> = PublishSubject.create()

    fun eventsObservable(): Observable<LogoQuizViewEvents> = events.hide().share()


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
}