package com.sazib.pinboard

import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.filters.LargeTest
import androidx.test.rule.ActivityTestRule
import androidx.test.runner.AndroidJUnit4
import com.sazib.pinboard.R.id
import com.sazib.pinboard.TestUtils.sleep
import com.sazib.pinboard.ui.pinboard.view.PinboardActivity
import com.sazib.pinboard.ui.pinboard.view.adapter.PinboardAdapter
import org.junit.FixMethodOrder
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.MethodSorters

@RunWith(AndroidJUnit4::class)
@LargeTest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
class PinboardActivityTest {
  @Rule
  @JvmField
  val mActivityRule: ActivityTestRule<PinboardActivity> =
    ActivityTestRule(PinboardActivity::class.java)

  @Test
  fun checkHomeRowClick() {
    sleep()
    //0
    Espresso.onView(ViewMatchers.withId(id.listPinboard))
        .perform(
            RecyclerViewActions.actionOnItemAtPosition<PinboardAdapter.ViewHolder>(
                0, ViewActions.click()
            )
        )
//1
    Espresso.onView(withId(id.listPinboard))
        .perform(
            RecyclerViewActions.actionOnItemAtPosition<PinboardAdapter.ViewHolder>(
                1, ViewActions.click()
            )
        )
    //pressBack()
  }

}