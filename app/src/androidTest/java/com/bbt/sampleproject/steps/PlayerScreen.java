package com.bbt.sampleproject.steps;


import com.bbt.sampleproject.R;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static org.hamcrest.Matchers.not;

public class PlayerScreen {

    public static void isScreenDisplayed() {
        onView(withId(R.id.player_name)).check(matches(isDisplayed()));
    }

    public static void isLastLoginDisplayed() {
        onView(withId(R.id.player_last_login)).check(matches(isDisplayed()));
    }

    public static void isLastLoginHidden() {
        onView(withId(R.id.player_last_login)).check(matches(not(isDisplayed())));
    }
}
