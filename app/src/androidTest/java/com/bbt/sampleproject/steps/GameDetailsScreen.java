package com.bbt.sampleproject.steps;


import com.bbt.sampleproject.R;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;

public class GameDetailsScreen {

    public static void isScreenDisplayed() {
        onView(withId(R.id.game_session_details_name)).check(matches(isDisplayed()));
    }
}
