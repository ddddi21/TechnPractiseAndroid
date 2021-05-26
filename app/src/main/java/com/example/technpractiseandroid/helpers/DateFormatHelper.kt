package com.example.technpractiseandroid.helpers
import java.text.SimpleDateFormat
import java.time.Month
import java.util.*

enum class DateFormatHelper(private val format: String, private val utcFixed: Boolean = false) {

    SimpleDate("yyyy-MM-dd"),
    DotsDate("dd.MM.yyyy"),
    FullDate("yyyy-MM-dd HH:mm:ss"),
    LessonCreateDate("dd MMM yyyy"),
    LessonCreateDateWithTime("dd MMM yyyy HH:mm"),
    DayMonth("dd MMM"),
    SimpleTime("HH:mm"),
    AbsoluteFullTime("HH:mm:ss", true),
    FullTime("HH:mm:ss"),
    HourOfDayTime("HH"),
    HourAndMinuteForDuration("H:mm");

    private val formatter = SimpleDateFormat(format, RUSSIA)

    init {
        if (utcFixed) {
            formatter.timeZone = TimeZone.getTimeZone("UTC")
        }
    }

    fun parse(string: String): Date? = formatter.parse(string)

    fun parseToCalendar(string: String): Calendar? =
        parse(string)?.let { Calendar.getInstance().apply { time = it } }

    fun format(date: Date): String = formatter.format(date)

    fun formatCalendar(calendar: Calendar): String = formatter.format(calendar.time)

//    fun translateMineToNormalDateFormat(date: String): {
//    }
}

private val RUSSIA by lazy { Locale("ru", "RU") }