package com.bbt.sampleproject.tests;

import android.app.Activity;
import android.content.Intent;
import android.support.test.espresso.intent.rule.IntentsTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
abstract class BaseEspressoTest<T extends Activity> {

    @Rule
    public IntentsTestRule mActivityRule = new IntentsTestRule<>(getActivityUnderTestClass(), false, false);

    @Before
    public void beforeTest() {
        mActivityRule.launchActivity(new Intent());
    }

    @After
    public void afterTest() {
        mActivityRule.getActivity().finish();
    }

    protected abstract Class<T> getActivityUnderTestClass();

}
