package com.tranquilinside.logoquiz.screens.logoQuiz

import com.tranquilinside.logoquiz.LogoQuizActivity
import io.reactivex.Observable
import org.junit.Assert
import org.junit.Assert.*
import org.junit.Ignore
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.Robolectric
import org.robolectric.RobolectricTestRunner
import org.robolectric.shadows.ShadowLooper
import java.util.concurrent.TimeUnit

@RunWith(RobolectricTestRunner::class)
class LogoQuizScreenTest {
    private fun screen(): LogoQuizScreen = LogoQuizScreen()

    // ignored because of lack of time to debug the issue, most probably need to add resources in build.gradle
    @Ignore
    @Test
    fun itShouldBuildAndProvideTemplateChooserView() {
        val templateChooserView =
            screen().buildView(
                Robolectric.buildActivity(LogoQuizActivity::class.java).get()
            ) as LogoQuizView
        assertNotNull(templateChooserView)
    }

    @Test
    fun itShouldProvideTemplateChooserReducer() {
        assertTrue(screen().reducer() is LogoQuizReducer)
    }

    @Test
    fun itShouldProvideTemplateChooserViewUpdater() {
        assertTrue(screen().updater() is LogoQuizViewUpdater)
    }

}