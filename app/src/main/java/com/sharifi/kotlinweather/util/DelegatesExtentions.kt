package com.sharifi.kotlinweather.util

import android.content.Context
import com.sharifi.kotlinweather.App
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

/**
 * Created by sharifi on 9/10/17.
 */
object DelegateExt {
    fun longPreference(name: String, default: Long) = LongPreference(App.instance, name, default)
}
class LongPreference(val context: Context, val name: String, val default: Long) : ReadWriteProperty<Any?, Long> {
    val prefs by lazy { context.getSharedPreferences("default", Context.MODE_PRIVATE) }

    override fun getValue(thisRef: Any?, property: KProperty<*>): Long {
        return prefs.getLong(name, default)
    }

    override fun setValue(thisRef: Any?, property: KProperty<*>, value: Long) {
        prefs.edit().putLong(name, value).apply()
    }
}