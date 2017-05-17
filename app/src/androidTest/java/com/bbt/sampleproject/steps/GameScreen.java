package com.bbt.sampleproject.steps;


import android.support.test.espresso.contrib.RecyclerViewActions;

import com.bbt.sampleproject.R;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;

public class GameScreen {

    public static void isScreenDisplayed() {
        onView(withId(R.id.game_sessions_recycler_view)).check(matches(isDisplayed()));
    }

    public static void clickFirstGame() {
        onView(withId(R.id.game_sessions_recycler_view)).perform(RecyclerViewActions.actionOnItemAtPosition(1, click()));
    }
}
