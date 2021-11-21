package com.tranquilinside.logoquiz

import android.annotation.SuppressLint
import android.content.Context
import android.os.Build
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.view.View
import com.tranquilinside.logoquiz.databinding.ActivityMainBinding
import com.tranquilinside.logoquiz.entities.LogoQuizItem
import com.tranquilinside.logoquiz.repository.LogoQuizDataManager
import com.tranquilinside.logoquiz.repository.LogoQuizDataRepository
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import kotlin.streams.asSequence

class LogoQuizActivity : AppCompatActivity(), RootView {

    private lateinit var binding: ActivityMainBinding
    val logoQuizDataRepository : LogoQuizDataRepository by lazy {
        LogoQuizDataManager(this)
    }
    @SuppressLint("CheckResult")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()
        val (initialState, listOfEventObservables) = buildInitialState()

        Observable.merge(listOfEventObservables)
            .scan(initialState, { state, event ->
                state.currentScreen.reducer().reduce(state, event)
            })
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe { state ->
                state.currentScreen.updater().update(state, this)
            }
    }

    private fun buildInitialState(): Pair<State, List<Observable<out Event>>> {
        return State().let { it.copy(currentScreen = it.logoQuizScreen) }.let {
            Pair(
                it,
                listOf(
                    it.logoQuizScreen.eventsObservable(),
                    Observable.just(Event.LoadRandomQuizData)
                )
            )
        }
    }

    override fun getRandomQuizData(): LogoQuizItem? {
        return logoQuizDataRepository.getRandomLogoQuizData()
    }

    override fun getJumbledAlphabetList(): String {
        val source = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            java.util.Random().ints(26, 0, source.length)
                .asSequence()
                .map(source::get)
                .joinToString("")
        } else {
            // this is not a good way to generate random strings as it supports android N + but this
            // doesn't compromise the structure of the code
            return "ZEBRAKMSDLOKOWDQLDMNUG"
        }
    }

    override fun getContext(): Context = this


    override fun updateView(view: View) {
        binding.rootLayout.removeAllViews()
        binding.rootLayout.addView(view)
    }

    override fun currentView(): View =  binding.rootLayout.getChildAt(0)

    override fun showProgressBar() {
        binding.inProgress.visibility = View.VISIBLE
    }

    override fun hideProgressBar() {
        binding.inProgress.visibility = View.GONE
    }
}