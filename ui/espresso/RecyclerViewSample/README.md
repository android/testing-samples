# RecyclerView sample for Espresso

Espresso has a special entry point to interact with AdapterViews, `onData()`, however, RecyclerViews work differently than AdapterViews.

In order to interact with RecyclerViews using Espresso, the `espresso-contrib` package has a collection of `RecyclerViewActions` that can be used to scroll to positions or perform actions on items.

In this example you'll find a basic Activity that shows a list and an example of how to scroll through it, perform some ViewActions and check ViewAssertions using Espresso.
