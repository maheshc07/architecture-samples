package com.example.android.architecture.blueprints.todoapp.tasks;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.doesNotExist;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isChecked;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import androidx.test.core.app.ActivityScenario;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import com.example.android.architecture.blueprints.todoapp.R;
import com.example.android.architecture.blueprints.todoapp.accessibilityidentifiers.AccessibilityIdentifiers;
import com.example.android.architecture.blueprints.todoapp.data.ConstantsValue;

import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
public class TaskFragmentTest {

    public ActivityScenario<TasksActivity> scenario;

    @Test
    public void verifyAddTwoTaskInTodoList() {
        scenario = ActivityScenario.launch(TasksActivity.class);
        onView(withId(R.id.tasks_linear_layout)).perform(click());
        onView(withId(R.id.add_task_title_edit_text)).perform(typeText(ConstantsValue.TASK1));
        onView(withId(R.id.add_task_description_edit_text)).perform(typeText(ConstantsValue.TEST_DATA));
        onView(withId(R.id.save_task_fab)).perform(click());
        onView(withId(R.id.add_task_title_edit_text)).perform(typeText(ConstantsValue.TASK2));
        onView(withId(R.id.add_task_description_edit_text)).perform(typeText(ConstantsValue.TEST_DATA));
        onView(withId(R.id.save_task_fab)).perform(click());
        onView(withId(R.id.menu_filter)).perform(click());
        onView(withText(AccessibilityIdentifiers.ALL)).perform(click());
        onView(withText(ConstantsValue.TASK1)).check(matches(isDisplayed()));
        onView(withText(ConstantsValue.TASK2)).check(matches(isDisplayed()));
    }

    @Test
    public void verifyMarkOneTaskCompleted() {
        scenario = ActivityScenario.launch(TasksActivity.class);
        onView(withId(R.id.complete_checkbox)).perform(click());
        onView(withId(R.id.complete_checkbox)).check(matches(isChecked()));
    }

    @Test
    public void verifyActiveTaskAndCompleteTaskInStatistics() {
        scenario = ActivityScenario.launch(TasksActivity.class);
        onView(withId(R.id.drawer_layout)).perform(click());
        onView(withText(AccessibilityIdentifiers.STATISTICS)).perform(click());
        onView(withText(AccessibilityIdentifiers.ACTIVETASK50)).check(matches(isDisplayed()));
        onView(withText(AccessibilityIdentifiers.COMPLETEDTASK50)).check(matches(isDisplayed()));
    }

    @Test
    public void verifyCompleteTaskDeletedAndClearedInTodoList() {
        scenario = ActivityScenario.launch(TasksActivity.class);
        onView(withText(ConstantsValue.TASK1)).perform(click());
        onView(withId(R.id.menu_delete)).perform(click());
        onView(withId(R.id.menu_filter)).perform(click());
        onView(withText(AccessibilityIdentifiers.ALL)).perform(click());
        onView(withText(ConstantsValue.TASK1)).check(doesNotExist());
    }
}
