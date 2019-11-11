package com.benjaminledet.pokedex.extensions

import android.content.Context
import android.content.Intent
import android.graphics.drawable.Drawable
import android.util.TypedValue
import android.widget.Toast
import androidx.annotation.AttrRes
import androidx.core.os.bundleOf

inline fun <reified T: Any> Context.intentFor(vararg pairs: Pair<String, Any?>): Intent = Intent(this, T::class.java).apply {
    if (pairs.isNotEmpty()) putExtras(bundleOf(*pairs))
}

fun Context.toast(message: CharSequence): Toast = Toast
    .makeText(this, message, Toast.LENGTH_SHORT)
    .apply {
        show()
    }

fun Context.longToast(message: CharSequence): Toast = Toast
    .makeText(this, message, Toast.LENGTH_LONG)
    .apply {
        show()
    }

fun Context.getAttributeFromAttr(@AttrRes attrRes: Int): Int {
    val typedValue = TypedValue()
    theme.resolveAttribute (attrRes, typedValue, true)
    return typedValue.data
}

fun Context.getDrawableFromAttr(@AttrRes attrRes: Int): Drawable? {
    val attrs = intArrayOf(attrRes)
    val typedArray = obtainStyledAttributes(attrs)
    val drawableFromTheme = typedArray.getDrawable(0)
    typedArray.recycle()
    return drawableFromTheme
}