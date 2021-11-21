package com.tranquilinside.logoquiz.screens.logoQuiz

import com.tranquilinside.logoquiz.LogoQuizActivity
import com.tranquilinside.logoquiz.RootView
import org.junit.runner.RunWith
import org.mockito.Mockito
import org.robolectric.Robolectric
import org.robolectric.RobolectricTestRunner

@RunWith(RobolectricTestRunner::class)
class LogoQuizViewUpdaterTest{


    private fun viewUpdater(): LogoQuizViewUpdater = LogoQuizViewUpdater()

    private fun mainActivity(): LogoQuizActivity {
        return Robolectric.setupActivity(LogoQuizActivity::class.java)
    }

    private fun rootView(): RootView {
        return Mockito.mock(RootView::class.java)
    }



}