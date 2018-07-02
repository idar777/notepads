package com.example.aydar.listnotepades.autorization;

import android.support.test.espresso.ViewInteraction;
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
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withHint;
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
        checkTitleOfScreen(onView(withId(R.id.entrance_text_view)), "ВХОД");
        onView(withId(R.id.login_edit_text)).perform(typeText("2@2.ru"));
        openRegistrationScreen();
        checkLoginField();
        checkPasswordField();
        performLoginField();
        performPasswordField();
        checkButtonConfirm();
        performButtonConfirm();
    }

    private void openRegistrationScreen(){
        ViewInteraction regButtonInteraction = onView(withId(R.id.registration_button));
        checkTitleOfScreen(regButtonInteraction, "Регистрация");
        regButtonInteraction.check(matches(isDisplayed()));
        regButtonInteraction.perform(click());
    }

    private void checkTitleOfScreen(ViewInteraction viewInteraction, String title){
        viewInteraction.check(matches(withText(title)));
    }

    private void checkLoginField(){
        checkTitleOfScreen(onView(withId(R.id.login_text_view)), "Логин");
    }

    private void checkPasswordField(){
        checkTitleOfScreen(onView(withId(R.id.password_text_view)), "Пароль");
    }

    private void performLoginField(){
        onView(withId(R.id.login_edit_text)).check(matches(withHint("Логин")));
        onView(withId(R.id.login_edit_text)).perform(typeText("2@2.ru"));
    }

    private void performPasswordField(){
        onView(withId(R.id.password_edit_text)).check(matches(withHint("Пароль")));
        onView(withId(R.id.password_edit_text)).perform(typeText("123"));
    }

    private void checkButtonConfirm(){
        checkTitleOfScreen(onView(withId(R.id.confirm_button)), "Подтвердить");
    }
    private void performButtonConfirm(){
        ViewInteraction regButtonInteraction = onView(withId(R.id.confirm_button));
        checkTitleOfScreen(regButtonInteraction, "Подтвердить");
        regButtonInteraction.check(matches(isDisplayed()));
        regButtonInteraction.perform(click());
    }
}