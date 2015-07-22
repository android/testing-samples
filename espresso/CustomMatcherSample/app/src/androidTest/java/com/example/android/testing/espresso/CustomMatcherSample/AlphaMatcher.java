package com.example.android.testing.espresso.CustomMatcherSample;

import android.support.test.espresso.matcher.BoundedMatcher;
import android.view.View;
import android.widget.TextView;
import org.hamcrest.Description;
import org.hamcrest.Matcher;

import static android.support.test.espresso.core.deps.guava.base.Preconditions.checkArgument;
import static android.support.test.espresso.core.deps.guava.base.Preconditions.checkNotNull;
import static org.hamcrest.Matchers.is;

/**
 * A custom matcher that checks the alpha property of a {@link android.view.View}. It
 * accepts either a {@link Float} or a {@link org.hamcrest.Matcher}.
 */
public class AlphaMatcher {

  static Matcher<View> withAlpha(Float expectedAlpha) {
    checkArgument(expectedAlpha > 0);
    return withAlpha(is(expectedAlpha));
  }

  static Matcher<View> withAlpha(final Matcher<Float> floatMatcher) {
    checkNotNull(floatMatcher);
    return new BoundedMatcher<View, TextView>(TextView.class) {

      @Override protected boolean matchesSafely(TextView view) {
        return floatMatcher.matches(view.getAlpha());
      }

      @Override public void describeTo(Description description) {
        description.appendText("Alpha expected: ");
        floatMatcher.describeTo(description);
      }
    };
  }
}