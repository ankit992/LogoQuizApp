package com.tranquilinside.logoquiz.screens.logoQuiz

import android.annotation.SuppressLint
import com.tranquilinside.logoquiz.*
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.subjects.PublishSubject

class LogoQuizViewUpdater: ViewUpdater {
    @SuppressLint("CheckResult")
    override fun update(state: State, rootView: RootView) {
        state.actions.observeOn(AndroidSchedulers.mainThread()).subscribe { action ->
            when (action) {
                Action.ShowCurrentScreen -> {
                    rootView.updateView(state.currentScreen.buildView(rootView.getContext()))
                }
                Action.GetRandomQuizData -> {
                    val randomQuizData = rootView.getRandomQuizData()
                    randomQuizData?.let {
                        (rootView.currentView() as LogoQuizView).updateRandomQuizData(it)
                        (rootView.currentView() as LogoQuizView).setJumbledAlphabetList(rootView.getJumbledAlphabetList())
                        events.onNext(Event.UpdateCurrentQuizData(it))
                    }
                }
            }
        }
    }

    private val events: PublishSubject<Event> = PublishSubject.create()

    override fun eventsObservable(): Observable<Event> {
        return events.hide().share()
    }
}