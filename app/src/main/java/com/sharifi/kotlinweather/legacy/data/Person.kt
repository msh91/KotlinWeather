package com.sharifi.kotlinweather.legacy.data

/**
 * Created by mohammad on 7/19/17.
 */

public class Person {
    var firstName: String = ""
        get() {
            return field.toUpperCase()
        }
        set(value) {
            field = "Name $value"
        }

    var lastName: String = ""
        get() = field.toUpperCase()

    override fun toString(): String {
        return "$firstName $lastName"
    }
}
