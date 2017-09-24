package com.sharifi.kotlinweather.legacy.data.commands

/**
 * Created by sharifi on 7/21/17.
 */
public interface Command<out T> {
    fun execute(): T
}