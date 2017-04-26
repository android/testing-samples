# Basic Idling Resource sample for Espresso

The centerpiece of Espresso is its ability to seamlessly synchronize all test operations with the application under test. By default, Espresso waits for UI events in the current message queue to be processed and default AsyncTasks* to complete before it moves on to the next test operation. This should address the majority of application/test synchronization in your application.

However, there are instances where applications perform background operations (such as communicating with web services) via non-standard means; for example: direct creation and management of threads.

This sample showcases how to implement a very simple IdlingResource interface and expose it to a test. The application shows a message to the user after a delay that is executed on a different thread.

Consider using the CountingIdlingResource class from the espresso-contrib package. It's a very easy to use Idling Resource implementation that can handle multiple parallel operations keeping track of the number of pending operations.

Note that the `espresso-idling-resource` dependency is added into the `compile` scope:

```
    androidTestCompile 'com.android.support.test.espresso:espresso-core:2.2.2'
    compile 'com.android.support.test.espresso:espresso-idling-resource:2.2.2'
```

This dependency and its implementation are added to the app under test but are not needed in production. This bloats the released app but it's kept this way to simplify the sample. You can:
 * ProGuard/shrink your release build to minimize impact
 * Use a build type or product flavor for tests and remove the Idling Resource classes in the production/release variant.
 * Add the dependency to `androidTestCompile` and inject an IdlingResource-aware MessageDelayer from the test.
 * Keep them, since the added methods and size are insignificant.


