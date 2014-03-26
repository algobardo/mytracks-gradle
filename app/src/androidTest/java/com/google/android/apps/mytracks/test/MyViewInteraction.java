package com.google.android.apps.mytracks.test;

import static com.google.android.apps.common.testing.ui.espresso.matcher.ViewMatchers.isAssignableFrom;
import static com.google.android.apps.common.testing.ui.espresso.matcher.ViewMatchers.isDescendantOfA;
import static com.google.common.base.Preconditions.checkNotNull;

import com.google.android.apps.common.testing.ui.espresso.FailureHandler;
import com.google.android.apps.common.testing.ui.espresso.NoMatchingViewException;
import com.google.android.apps.common.testing.ui.espresso.PerformException;
import com.google.android.apps.common.testing.ui.espresso.Root;
import com.google.android.apps.common.testing.ui.espresso.UiController;
import com.google.android.apps.common.testing.ui.espresso.ViewAction;
import com.google.android.apps.common.testing.ui.espresso.ViewAssertion;
import com.google.android.apps.common.testing.ui.espresso.ViewFinder;
import com.google.android.apps.common.testing.ui.espresso.ViewInteraction;
import com.google.android.apps.common.testing.ui.espresso.action.ScrollToAction;
import com.google.android.apps.common.testing.ui.espresso.base.MainThread;
import com.google.android.apps.common.testing.ui.espresso.util.HumanReadables;
import com.google.common.base.Optional;

import android.util.Log;
import android.view.View;
import android.widget.AdapterView;

import org.hamcrest.Matcher;
import org.hamcrest.StringDescription;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.FutureTask;
import java.util.concurrent.atomic.AtomicReference;

import javax.inject.Inject;

/**
 * Provides the primary interface for test authors to perform actions or asserts on views.
 * <p>
 * Each interaction is associated with a view identified by a view matcher. All view actions and
 * asserts are performed on the UI thread (thus ensuring sequential execution). The same goes for
 * retrieval of views (this is done to ensure that view state is "fresh" prior to execution of each
 * operation).
 * <p>
 */
public final class MyViewInteraction {

  private ViewInteraction proxiedvi = null;
  @Inject
  MyViewInteraction(ViewInteraction vi){
    proxiedvi = vi;
  }

  public MyViewInteraction perform(final ViewAction... viewActions) {
    proxiedvi = proxiedvi.perform(viewActions);
    return this;
  }


  public MyViewInteraction inRoot(Matcher<Root> rootMatcher) {
    proxiedvi.inRoot(rootMatcher);
    return this;
  }

  public MyViewInteraction check(final ViewAssertion viewAssert) {
        proxiedvi.check(viewAssert);
        return this;
  }

}
