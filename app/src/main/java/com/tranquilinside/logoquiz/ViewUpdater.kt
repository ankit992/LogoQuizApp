package com.tranquilinside.logoquiz

import io.reactivex.Observable

interface ViewUpdater {
    fun update(state: State, rootView: RootView)
    fun eventsObservable(): Observable<Event>
}
