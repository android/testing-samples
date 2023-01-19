import week1.ExampleObject
import week1.User
import java.util.Calendar
import kotlin.random.Random

fun main() {
    /*
    Kotlin Basics:
    - strongly typed
    - compiles into either Java or JS
    - interoperable with Java

    Basically, you can think of Kotlin as a more modern version of Java. It functions pretty much the same, but
    includes a lot of features that deal with problems in base Java.

    See Basics and Concepts sections of Kotlin docs. Unlike Javadocs, these are actually decent.
    https://kotlinlang.org/docs/home.html
     */

    val myJavaClass: MyJavaClass = MyJavaClass()
    println(myJavaClass.name)

    variableDeclaration()
    conditionals()
    loops()
    nullSafety()
    collections()
    dataClass()
}

private fun printSeparator(sectionName: String) {
    println("------------------------------------------------------")
    println(sectionName)
    println("------------------------------------------------------")
}

fun variableDeclaration() {
    printSeparator("Variable Declaration")
    /*
    Declare immutable variables with val, mutable variables with var (see bit on Mutability)
     */
    val myVar: String = "abc"
    // this will not compile, since myVar cannot be reassigned
    // myVar = "xyz"

    var myVar2: Int = 0
    myVar2 = 1 // this is fine

    /*
    Note that this is referential. Meaning if you assign an object to an immutable variable, the object itself can
    still be mutable, but the variable/reference to that object cannot change. Think pointers
     */
    val myObject: ExampleObject = ExampleObject()

    println("myObject.name: ${myObject.name}")
    myObject.name = "New name"
    println("myObject.name: ${myObject.name}")

    /*
    As an aside, you'll notice Kotlin supports native string interpolation. We can do "somestring ${some call}" and it
    will automatically wrap ${some call} in a toString() and interpolate it.
     */

    // if we want to just reference something directly, we don't need curly braces
    println("The first 3 letters of the alphabet are: $myVar")
    // equivalent in Java
    System.out.println("The first 3 letters of the alphabet are: " + myVar)

    /*
    Kotlin is also smart enough to infer types. However, note that variables are still strongly-typed, it just means
    that Kotlin knows that “abcd” is a string and 123 is an integer.
     */

    val myString = "abcd"

    // this will not compile, since myString is a String
    // myString = 123
}

fun conditionals() {
    // docs: https://kotlinlang.org/docs/control-flow.html
    printSeparator("Conditionals")
    // conditions are pretty straightforward. Just a matter of slightly different syntax between languages
    val n = 1
    println("IF EXAMPLE")
    if (n == 2) {
        println("$n > 2")
    } else if (n == 1) {
        println("$n > 1")
    } else {
        println("$n")
    }

    println("WHEN EXAMPLE")
    // we can also use when statements, which are Kotlin's equivalent of switch statements
    when (n) {
        1 -> println("do stuff for n == 1")
        2 -> println("do stuff for n == 2")
        else -> println("do other stuff for ")
    }

    // conditionals can also be used in assignments or as part of function returns
    val nString = if (n == 1) "one" else n.toString() // notice that if statements don't always need braces
    println("CONDITIONAL ASSIGNMENT EXAMPLE")
    println("n=$n, nString=$nString")
    // this also applies for functions
    println("random positive int: ${returnRandomInt()}")
    println("random negative int: ${returnRandomInt(true)}")
}

fun returnRandomInt(negative: Boolean = false) =
    if (negative) {
        Random.nextInt(Int.MIN_VALUE, 0)
    } else {
        Random.nextInt(1, Int.MAX_VALUE)
    }

fun loops() {
    // docs: https://kotlinlang.org/docs/control-flow.html
    printSeparator("Loops")

    println("FOR LOOP EXAMPLE 1")
    // for loops in Kotlin are generally pretty concise
    for (i in 0..10) {
        print("$i,")
    }
    println()

    println("FOR LOOP STEP EXAMPLE")
    // you can also natively specify step size and decreasing values
    for (i in 0..10 step 2) {
        print("$i,")
    }
    println()
    println("FOR LOOP DECREMENT EXAMPLE")
    for (i in 10 downTo 0) {
        print("$i,")
    }
    println()

    println("FOR LOOP RANGE EXAMPLE")
    // you can also iterate over ranges, since they're represented as integers under the hood
    val range = CharRange('a', 'c')
    for (i in range) {
        print("$i,")
    }
    println()
    for (i in 'x'..'z') {
        print(i)
    }
    println()

    // Kotlin also supports natively looping over collections
    val nums = listOf(0, 2, 4, 5, 0, 123)
    for (i in nums) {
        print("$i,")
    }

    // including iterating over indicies
    for (index in nums.indices) {
        print("nums[$index] = ${nums[index]}, ")
    }
    println()

    println("FOR EACH EXAMPLE")
    // this can also be done via methods like forEach or forEachIndexed
    nums.forEach {
        print(it)
    }
    println()
    println("FOR EACH INDEXED EXAMPLE")
    nums.forEachIndexed { i, n ->
        print("nums[$i] = $n,")
    }
    println()

    println("JOIN TO STRING EXAMPLE")
    // if we want a string representation of a collection, we can use joinToString()
    println(nums.joinToString())
}

fun nullSafety() {
    printSeparator("Null Safety")

    /*
    Variables in Kotlin need to explicitly be nullable. We do this by adding ? after type declaration
     */
    var myNullableVar: String? = "abcd"
    myNullableVar = null

    // this won't compile
    // var myVar: String = null

    /*
    Kotlin is also smart enough to know if you've done a null check
     */

    if (myNullableVar != null) {
        // we can safely assign myNullableVar to a non-nullable String here
        val myNonNullableString = myNullableVar
    }

    /*
    Kotlin also allows for safe calls with ?.
    This returns null if the caller is null. This helps to prevent NPE's potentially causing fatal crashes.
     */

    var exObject: ExampleObject? = null
    println("safe call example: ${exObject?.name}") // in Java, this would throw NPE
    exObject = ExampleObject()

    // kotlin is even smart enough to know that we've assigned exObject here, and no longer need safe call ?.
    println(exObject.name)

    /*
    We can also use Elvis operator ?: for cases where we want to specify a value when something returns null.

    someCall() ?: defaultValue

    This is basically equivalent to :
        if (someCall() == null) {
            defaultValue
        } else {
            someCall()
        }
     */

    exObject = null
    println("elvis operator example: ${exObject?.name ?: "default string"}")

    // we can also use it for assignments when our type is non-nullable
    val nonNullableString: String = exObject?.name ?: "default string"

    /*
     one common use case for our automation tests is to throw exceptions when conditions for tests aren't met
     We use objects and enums in practice, but for this example, we'll use strings for simplicity
     */

    fun exampleRun(hardware: String): String {
        // when statements are Kotlin's version of switches
        return when (hardware) {
            "TITAN", "PRISM", "V1BIKE", "AURORA", "CAESAR" -> "do stuff for $hardware"
            else -> throw RuntimeException("$hardware not supported")
        }
    }

    println("exampleRun(TITAN): ${exampleRun("TITAN")}")

    try {
        print("exampleRun(123): ${exampleRun("123")}")
    } catch (e: Throwable) {
        println(e)
    }
}

fun collections() {
    // https://kotlinlang.org/docs/collections-overview.html
    printSeparator("Collections")

    /*
    One big thing for Kotlin is that collections are by default immutable. This follows pretty similar logic to val/var
    for variable assignments, but also applies for the variables themselves. It helps to think of variables as pointers.

    Pointers can be mutable/immutable
    Collection itself can also be mutable/immutable

    So we have 4 combinations
     */

    val immutableListImmutableVariable: List<Int> = listOf(1, 2, 3)
    var immutableListMutableVariable: List<Int> = listOf(10, 20, 30)

    /*
    The following doesn't work. Neither of the above lists can have elements added/removed

    immutableListImmutableVariable.add(4)
    immutableListMutableVariable.add(2)
     */

    // however, we can REASSIGN immutableListMutableVariable since it's reference is declared with var
    immutableListMutableVariable = listOf(-10, -20, -30)
    println(immutableListMutableVariable)

    /*
    If we want a mutable list, we need to use MutableList objects
     */
    val mutableListImmutableVariable: MutableList<Int> = mutableListOf(100, 200, 300)
    var mutableListMutableVariable: MutableList<Int> = mutableListOf(1000, 2000, 3000)

    /*
    mutable collections inherit from collections, so we can assign mutable collections to collections, but not
    vice versa.
     */
    val myList: List<Int> = mutableListOf(0, 0, 0)
}

fun dataClass() {
    printSeparator("Data Classes")
    val birthday1 = Calendar.Builder().setDate(1990, 1, 1).build()
    val user1 = User("Bob", "abc", birthday1)
    println(user1.age)
}
