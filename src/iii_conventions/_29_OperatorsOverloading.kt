package iii_conventions

import util.TODO
import iii_conventions.TimeInterval.*

fun todoTask29(): Nothing = TODO(
    """
        Task 29.
        Implement a kind of date arithmetic. Support adding years, weeks and days to a date.
        Use classes MyDate and TimeInterval.
        Use a utility function MyDate.addTimeIntervals.
        Uncomment the commented line and make it compile.

        (1). Add an extension function 'plus()' to MyDate, taking a TimeInterval as an argument.
        (2). Support adding several time intervals to a date. Add an extra class.
        If you have any problems, see the iii_conventions/_29_Tips.kt file.
    """,
    references = { date: MyDate, timeInterval: TimeInterval ->
        date.addTimeIntervals(timeInterval, 1)
    })

fun task29_1(today: MyDate): MyDate {
    return today + YEAR + WEEK
}

private infix operator fun MyDate.plus(interval: TimeInterval): MyDate {
    when(interval) {
        DAY -> { return this.addTimeIntervals(DAY, 1) }
        WEEK -> { return this.addTimeIntervals(WEEK, 1) }
        YEAR -> { return this.addTimeIntervals(YEAR, 1) }
    }
}

private infix operator fun MyDate.plus(interval: RepeatedTimeInterval): MyDate {
    var that = this

    for (i in 1..interval.n) {
        that = that.plus(interval.ti)
    }

    return that
}

fun task29_2(today: MyDate): MyDate {
    return today + YEAR * 2 + WEEK * 3 + DAY * 5
}

class RepeatedTimeInterval(val ti: TimeInterval, val n: Int)

private infix operator fun TimeInterval.times(number: Int): RepeatedTimeInterval {
    return RepeatedTimeInterval(this, number)
}

