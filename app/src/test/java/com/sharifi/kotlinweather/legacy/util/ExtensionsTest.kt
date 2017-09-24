package com.sharifi.kotlinweather.legacy.util

import org.junit.Assert.assertEquals
import org.junit.Test
import java.text.DateFormat

/**
 * Created by sharifi on 9/10/17.
 */
class ExtensionsTest {
    @Test
    fun testLongToDateString() {
        assertEquals("Oct 19, 2015", 1445275635000L.toDateString())
    }

    @Test
    fun testToDateStringFullFormat() {
        assertEquals("Monday, October 19, 2015", 1445275635000L.toDateString(DateFormat.FULL))
    }

}