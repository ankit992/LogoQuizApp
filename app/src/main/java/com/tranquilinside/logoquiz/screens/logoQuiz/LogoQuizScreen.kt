package com.tranquilinside.logoquiz.screens.logoQuiz

import android.annotation.SuppressLint
import android.content.Context
import android.view.View
import com.tranquilinside.logoquiz.Event
import com.tranquilinside.logoquiz.Reducer
import com.tranquilinside.logoquiz.Screen
import com.tranquilinside.logoquiz.ViewUpdater
import io.reactivex.Observable
import io.reactivex.subjects.PublishSubject

class LogoQuizScreen: Screen {
    @SuppressLint("CheckResult")
    override fun updater(): ViewUpdater {
        val logoQuizViewUpdater = LogoQuizViewUpdater()
        logoQuizViewUpdater.eventsObservable().subscribe { event ->
            events.onNext(event)
        }
        return logoQuizViewUpdater
    }

    override fun reducer(): Reducer = LogoQuizReducer()

    private val events = PublishSubject.create<Event>()
    override fun eventsObservable(): Observable<Event> = events.hide().share()

    @SuppressLint("CheckResult")
    override fun buildView(context: Context): View {
        return LogoQuizView(context).also {
            it.eventsObservable().subscribe { event ->
                when (event) {
                    LogoQuizViewEvents.GetRandomQuizData -> events.onNext(Event.LoadData)
                }
            }
        }
    }
}