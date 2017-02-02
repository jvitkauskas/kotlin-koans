package iii_conventions

import java.time.LocalDate

data class MyDate(val year: Int, val month: Int, val dayOfMonth: Int) : Comparable<MyDate> {
    override fun compareTo(other: MyDate): Int {
        return LocalDate.of(year, month, dayOfMonth)
                .compareTo(LocalDate.of(other.year, other.month, other.dayOfMonth))
    }
}

operator fun MyDate.rangeTo(other: MyDate): DateRange = DateRange(this, other)

enum class TimeInterval {
    DAY,
    WEEK,
    YEAR
}

class DateRange(val start: MyDate, val endInclusive: MyDate) : Iterable<MyDate> {
    operator fun contains(date: MyDate): Boolean {
        return start <= date && date < endInclusive
    }

    override fun iterator(): Iterator<MyDate> {
        return DateRangeIterator(this)
    }
}

class DateRangeIterator(val range: DateRange) : Iterator<MyDate> {
    var current = range.start

    override fun hasNext(): Boolean {
        return current <= range.endInclusive
    }

    override fun next(): MyDate {
        try {
            return current
        } finally {
            current = current.nextDay()
        }
    }
}