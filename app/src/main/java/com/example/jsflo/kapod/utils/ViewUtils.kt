package com.example.jsflo.kapod.utils

import android.text.TextUtils
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.example.jsflo.kapod.R


fun View.show() = { visibility = View.VISIBLE }
fun View.hide() = { visibility = View.GONE }
fun Array<out View>.show() = this.map { it.show() }
fun Array<out View>.hide() = this.map { it.hide() }

fun ImageView.loadImg(imageUrl: String, placeholderDrawableRes: Int = R.mipmap.ic_launcher) {
    if (TextUtils.isEmpty(imageUrl)) {
        Glide.with(context).load(placeholderDrawableRes).into(this)
    } else {
        Glide.with(context).load(imageUrl).into(this)
    }
}

fun TextView.setTextOrHide(charSequence: CharSequence?, vararg views: View = arrayOf(this)) {
    if (charSequence == null) {
        views.hide()
    } else {
        text = charSequence
        views.show()
    }
}

fun Any?.debugPrint() = Log.d("debug", this?.toString() ?: "null item!!")