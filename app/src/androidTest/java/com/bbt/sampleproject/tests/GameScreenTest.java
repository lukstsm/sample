package com.bbt.sampleproject.tests;


import com.bbt.sampleproject.steps.GameDetailsScreen;
import com.bbt.sampleproject.steps.GameScreen;
import com.bbt.sampleproject.steps.PlayerScreen;
import com.bbt.sampleproject.ui.activity.GameActivity;

import org.junit.Test;

public class GameScreenTest extends BaseEspressoTest {

    @Test
    public void showPlayerInfo() {
        PlayerScreen.isScreenDisplayed();
    }

    @Test
    public void showDetailsScreenWhenGameSessionClicked() {
        GameScreen.clickFirstGame();
        GameDetailsScreen.isScreenDisplayed();
    }

    @Override
    protected Class getActivityUnderTestClass() {
        return GameActivity.class;
    }
}
