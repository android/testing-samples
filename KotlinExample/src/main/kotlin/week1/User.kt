package week1

import java.time.Instant
import java.util.Calendar
import java.util.Locale
import java.util.concurrent.TimeUnit

data class User(
    val name: String,
    val id: String,
    val birthday: Calendar,
    val locale: Locale = Locale.US
) {
    val age: Int
        get() {
            val ageMillis: Long = Instant.now().toEpochMilli() - birthday.toInstant().toEpochMilli()
            return (TimeUnit.DAYS.convert(ageMillis, TimeUnit.MILLISECONDS) / 365).toInt()
        }
}
