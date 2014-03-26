package com.google.android.apps.mytracks.test;

import static com.google.android.apps.common.testing.ui.espresso.action.ViewActions.click;
import static com.google.android.apps.common.testing.ui.espresso.action.ViewActions.pressMenuKey;
import static com.google.android.apps.common.testing.ui.espresso.matcher.ViewMatchers.isDisplayed;
import static com.google.android.apps.common.testing.ui.espresso.matcher.ViewMatchers.isRoot;
import static com.google.android.apps.common.testing.ui.espresso.matcher.ViewMatchers.withClassName;
import static com.google.android.apps.common.testing.ui.espresso.matcher.ViewMatchers.withContentDescription;
import static com.google.common.base.Preconditions.checkNotNull;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.anyOf;
import static org.hamcrest.Matchers.endsWith;
import org.jgrapht.*;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.Instrumentation;
import android.content.Context;
import android.os.Build;
import android.os.Looper;
import android.view.View;
import android.view.ViewConfiguration;

import com.google.android.apps.common.testing.ui.espresso.DataInteraction;
import com.google.android.apps.common.testing.ui.espresso.Espresso;
import com.google.android.apps.common.testing.ui.espresso.UiController;

import dagger.ObjectGraph;

import org.hamcrest.Matcher;
import org.jgrapht.graph.SimpleDirectedGraph;

import java.util.Collection;
import java.util.Set;

public final class MyEspresso {

    protected class EventDesc
    {

    }
    protected class ViewDesc
    {

    }

    DirectedGraph<ViewDesc,EventDesc> eventGraph = new SimpleDirectedGraph<ViewDesc, EventDesc>(EventDesc.class);


    private MyEspresso() {


    }

    public static MyViewInteraction onView(final Matcher<View> viewMatcher) {
    return new MyViewInteraction(Espresso.onView(viewMatcher));
    }

    public static DataInteraction onData(Matcher<Object> dataMatcher) {
    return Espresso.onData(dataMatcher);
    }

    public static void closeSoftKeyboard() {
    Espresso.closeSoftKeyboard();
    }

    public static void openContextualActionModeOverflowMenu() {
    Espresso.openContextualActionModeOverflowMenu();
    }

    public static void pressBack() {
    Espresso.pressBack();
    }

    public static void openActionBarOverflowOrOptionsMenu(Context context) {
    Espresso.openActionBarOverflowOrOptionsMenu(context);
    }

}
