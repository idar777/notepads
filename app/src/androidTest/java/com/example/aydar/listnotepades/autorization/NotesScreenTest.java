package com.example.aydar.listnotepades.autorization;

import android.support.test.espresso.ViewInteraction;
import android.support.test.espresso.action.ViewActions;
import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.ActivityInstrumentationTestCase2;

import com.example.aydar.listnotepades.R;
import com.example.aydar.listnotepades.presentation.view.StartActivity;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.closeSoftKeyboard;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withHint;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

/**
 * Created by Ivanova Maria on 28.11.17.
 */


@RunWith(AndroidJUnit4.class)
@LargeTest
public class NotesScreenTest extends ActivityInstrumentationTestCase2<StartActivity> {

    public NotesScreenTest() {
        super(StartActivity.class);
    }

    @Rule
    public ActivityTestRule<StartActivity> activityActivityTestRule = new ActivityTestRule<StartActivity>(StartActivity.class);

    @Test
    public void someTest() {
        openRegistrationScreen();
        performLoginField();
        performPasswordField();
        checkButtonConfirm();
        performButtonConfirm();
        checkNotesList();
        checkButtonCreate();
        checkCreateNameNote();
        checkCreateTextNote();
        checkButtonDelete();
        checkButtonSave();
        checkNotesListAfterCreate();
    }

    private void openRegistrationScreen() {
        ViewInteraction regButtonInteraction = onView(withId(R.id.registration_button));
        checkTitleOfScreen(regButtonInteraction, "Регистрация");
        regButtonInteraction.check(matches(isDisplayed()));
        regButtonInteraction.perform(click());
    }

    private void checkTitleOfScreen(ViewInteraction viewInteraction, String title) {
        viewInteraction.check(matches(withText(title)));
    }

    private void performLoginField() {
        onView(withId(R.id.login_edit_text)).check(matches(withHint("Логин")));
        onView(withId(R.id.login_edit_text)).perform(typeText("3@2.ru"));
    }

    private void performPasswordField() {
        onView(withId(R.id.password_edit_text)).check(matches(withHint("Пароль")));
        onView(withId(R.id.password_edit_text)).perform(typeText("123"));
    }

    private void checkButtonConfirm() {
        checkTitleOfScreen(onView(withId(R.id.confirm_button)), "Подтвердить");
    }

    private void performButtonConfirm() {
        ViewInteraction regButtonInteraction = onView(withId(R.id.confirm_button));
        checkTitleOfScreen(regButtonInteraction, "Подтвердить");
        regButtonInteraction.check(matches(isDisplayed()));
        regButtonInteraction.perform(click());
    }

    private void checkNotesList(){
        onView(withId(R.id.notes_recycler_view)).check(new RecyclerViewItemCountAssertion(0));
    }

    private void checkButtonCreate() {
        ViewInteraction createButtonInteraction = onView(withId(R.id.create_note_button));
        checkTitleOfScreen(createButtonInteraction, "Создать");
        createButtonInteraction.check(matches(isDisplayed()));
        createButtonInteraction.perform(click());
    }

    private void checkCreateNameNote() {
        onView(withId(R.id.name_note_edit_text)).check(matches(withHint("Наименование")));
        onView(withId(R.id.name_note_edit_text)).perform(typeText("First note"));
    }

    private void checkCreateTextNote() {

        onView(withId(R.id.content_edit_text)).perform(typeText("Note"));
    }

    private void checkButtonDelete() {
        ViewInteraction deleteButtonInteraction = onView(withId(R.id.delete_button));
        checkTitleOfScreen(deleteButtonInteraction, "Удалить");
        deleteButtonInteraction.check(matches(isDisplayed()));
    }

    private void checkButtonSave() {
        ViewInteraction saveButtonInteraction = onView(withId(R.id.save_button));
        checkTitleOfScreen(saveButtonInteraction, "Сохранить");
        saveButtonInteraction.check(matches(isDisplayed()));
        saveButtonInteraction.perform(ViewActions.closeSoftKeyboard());
        saveButtonInteraction.perform(click());
    }

    private void checkNotesListAfterCreate(){
        onView(withId(R.id.notes_recycler_view)).check(new RecyclerViewItemCountAssertion(1));
    }

}