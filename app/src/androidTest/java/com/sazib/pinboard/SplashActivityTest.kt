package com.sazib.pinboard

import androidx.test.filters.LargeTest
import androidx.test.rule.ActivityTestRule
import androidx.test.runner.AndroidJUnit4
import com.sazib.pinboard.ui.splash.view.SplashActivity
import org.junit.FixMethodOrder
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.MethodSorters

@RunWith(AndroidJUnit4::class)
@LargeTest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
class SplashActivityTest {
  @Rule
  @JvmField
  val mActivityRule: ActivityTestRule<SplashActivity> =
    ActivityTestRule(SplashActivity::class.java)

  @Test
  fun checkSplash() {

  }

}