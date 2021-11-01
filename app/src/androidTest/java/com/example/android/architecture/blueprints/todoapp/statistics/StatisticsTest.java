package com.example.android.architecture.blueprints.todoapp.statistics;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import androidx.test.core.app.ActivityScenario;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import com.example.android.architecture.blueprints.todoapp.R;
import com.example.android.architecture.blueprints.todoapp.accessibilityidentifiers.AccessibilityIdentifiers;
import com.example.android.architecture.blueprints.todoapp.tasks.TasksActivity;

import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
public class StatisticsTest {

    public ActivityScenario<TasksActivity> scenario;

    @Test
    public void verifyActiveTaskAndCompleteTaskInStatistics() {
        scenario = ActivityScenario.launch(TasksActivity.class);
        onView(withId(R.id.drawer_layout)).perform(click());
        onView(withText(AccessibilityIdentifiers.STATISTICS)).perform(click());
        onView(withText(AccessibilityIdentifiers.ACTIVETASK50)).check(matches(isDisplayed()));
        onView(withText(AccessibilityIdentifiers.COMPLETEDTASK50)).check(matches(isDisplayed()));
    }
}
