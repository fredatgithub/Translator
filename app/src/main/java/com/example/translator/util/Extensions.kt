package com.example.translator.util

import android.content.Context
import android.content.Context.CONNECTIVITY_SERVICE
import android.net.ConnectivityManager
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.View.*
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.Toast.*
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import java.util.concurrent.TimeUnit

fun Context.isNetworkAvailable(): Boolean {
    val manager = getSystemService(CONNECTIVITY_SERVICE) as ConnectivityManager?

    return manager?.activeNetworkInfo?.isConnectedOrConnecting ?: false
}

fun Context.shortToast(message: String) = makeText(this, message, LENGTH_SHORT).show()

fun Context.longToast(message: String) = makeText(this, message, LENGTH_LONG).show()

fun ViewGroup.inflate(layoutRes: Int): View = LayoutInflater.from(context).inflate(layoutRes, this, false)

fun View.visible() { visibility = VISIBLE }

fun View.invisible() { visibility = INVISIBLE }

fun View.gone() { visibility = GONE }

fun View.hideKeyboard() {
    val manager = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    manager.hideSoftInputFromWindow(windowToken, InputMethodManager.HIDE_NOT_ALWAYS)
}

inline fun <T> LiveData<T>.observe(
    owner: LifecycleOwner,
    crossinline observer: (T) -> Unit
) {
    this.observe(owner, Observer { it?.apply(observer) })
}

fun EditText.isEmpty(): Boolean = value.isBlank()

val EditText.value
    get() = text.toString()

fun runDelayed(delay: Long, action: () -> Unit) {
    Handler().postDelayed(action, TimeUnit.MILLISECONDS.toMillis(delay))
}