package com.tranquilinside.logoquiz


interface Reducer {
    fun reduce(state: State, event: Event): State
}
