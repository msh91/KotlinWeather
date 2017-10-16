package com.sharifi.kotlinweather

import org.mockito.Mockito

/**
 * Created by sharifi on 10/16/17.
 */
inline fun <reified T : Any> mock(): T = Mockito.mock(T::class.java)