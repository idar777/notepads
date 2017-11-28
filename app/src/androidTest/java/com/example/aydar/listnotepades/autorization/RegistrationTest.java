package com.example.aydar.listnotepades.autorization;

import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.ActivityInstrumentationTestCase2;

import com.example.aydar.listnotepades.R;
import com.example.aydar.listnotepades.presentation.view.StartActivity;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

/**
 * Created by aydar on 28.11.17.
 */
@RunWith(AndroidJUnit4.class)
@LargeTest
public class RegistrationTest extends ActivityInstrumentationTestCase2<StartActivity> {

    public RegistrationTest() {
        super(StartActivity.class);
    }

    @Rule
    public ActivityTestRule<StartActivity> activityActivityTestRule = new ActivityTestRule<StartActivity>(StartActivity.class);

    @Test
    public void someTest() {
        onView(withId(R.id.entrance_text_view)).check(matches(withText("ВХОД")));
        onView(withId(R.id.login_edit_text)).perform(typeText("2@2.ru"));
        onView(withId(R.id.login_edit_text)).perform(typeText("2@2.ru"));

    }
}

