package week1

import java.time.Instant
import java.time.LocalDate
import java.time.ZoneOffset
import java.util.Locale
import java.util.concurrent.TimeUnit

data class User(
    val name: String,
    val id: String,
    val birthday: LocalDate,
    val locale: Locale = Locale.US
) {
    val age: Int
        get() {
            val ageMillis: Long = Instant.now().toEpochMilli() - birthday.atStartOfDay().toInstant(ZoneOffset.UTC).toEpochMilli()
            return (TimeUnit.DAYS.convert(ageMillis, TimeUnit.MILLISECONDS) / 365).toInt()
        }
}
