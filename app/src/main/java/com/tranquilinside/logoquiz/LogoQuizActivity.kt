package com.tranquilinside.logoquiz

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import android.view.Menu
import android.view.MenuItem
import android.view.View
import com.tranquilinside.logoquiz.databinding.ActivityMainBinding
import com.tranquilinside.logoquiz.entities.LogoQuizItem
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers

class LogoQuizActivity : AppCompatActivity(), RootView {

    private lateinit var binding: ActivityMainBinding

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
                    Observable.just(Event.LoadData)
                )
            )
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