package com.sharifi.kotlinweather.util

import android.os.Bundle
import android.os.Parcelable
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentActivity
import android.support.v4.app.FragmentTransaction
import com.sharifi.kotlinweather.base.BaseActivity
import com.sharifi.kotlinweather.base.BaseFragment
import org.jetbrains.anko.AnkoException
import java.io.Serializable
import java.util.*

/**
 * Created by sharifi on 10/4/17.
 */
val Fragment.NAME: String
    get() = this::class.java.name

fun FragmentActivity.loadFragment(loadToBackStack: Boolean = false, load: FragmentTransaction.() -> String) {
    if (this is BaseActivity && !activityStarted) {
        return
    }
    val transaction = supportFragmentManager.beginTransaction()
    val fragmentName = transaction.load()
    if (loadToBackStack) {
        transaction.addToBackStack(fragmentName)
    }
    transaction.commit()

}

fun Fragment.loadFragment(loadToBackStack: Boolean = false, useChildFragmentManager: Boolean = false, load: FragmentTransaction.() -> String) {
    if (this is BaseFragment && !fragmentStarted) {
        return
    }

    if (!useChildFragmentManager) {
        activity.loadFragment(loadToBackStack, load)
        return
    }

    val transaction = childFragmentManager.beginTransaction()
    val fragmentName = transaction.load()
    if (loadToBackStack) {
        transaction.addToBackStack(fragmentName)
    }
    transaction.commit()

}

fun <T : Fragment> T.withArgs(vararg params: Pair<String, Any?>): T {
    val args = Bundle()
    if (params.isNotEmpty()) args.fill(params)
    arguments = args
    return this
}

fun Bundle.fill(params: Array<out Pair<String, Any?>>) = apply {
    params.forEach {
        val value = it.second
        when (value) {
            null -> putSerializable(it.first, null as Serializable?)
            is Int -> putInt(it.first, value)
            is Long -> putLong(it.first, value)
            is CharSequence -> putCharSequence(it.first, value)
            is String -> putString(it.first, value)
            is Float -> putFloat(it.first, value)
            is Double -> putDouble(it.first, value)
            is Char -> putChar(it.first, value)
            is Short -> putShort(it.first, value)
            is Boolean -> putBoolean(it.first, value)
            is Serializable -> putSerializable(it.first, value)
            is Bundle -> putBundle(it.first, value)
            is Parcelable -> putParcelable(it.first, value)
            is LongArray -> putLongArray(it.first, value)
            is FloatArray -> putFloatArray(it.first, value)
            is DoubleArray -> putDoubleArray(it.first, value)
            is CharArray -> putCharArray(it.first, value)
            is ShortArray -> putShortArray(it.first, value)
            is BooleanArray -> putBooleanArray(it.first, value)
            is IntArray -> putIntArray(it.first, value)
            is Array<*> -> when {
                value.isArrayOf<CharSequence>() -> putCharSequenceArray(it.first, value as Array<out CharSequence>)
                value.isArrayOf<String>() -> putStringArray(it.first, value as Array<out String>)
                value.isArrayOf<Parcelable>() -> putParcelableArrayList(it.first, value as ArrayList<out Parcelable>)
                else -> throw AnkoException("Bundle extra ${it.first} has wrong type ${value.javaClass.name}")
            }
            else -> throw AnkoException("Bundle extra ${it.first} has wrong type ${value.javaClass.name}")
        }
    }
}
