package com.kinematik.themoviedb.themoviedbappclean.presentation.ui.extensions

import android.view.View
import android.view.ViewTreeObserver

fun <T : View> T?.afterPreDraw(what: (view: T, width: Int, height: Int) -> Unit) {
    this?.let {
        var isFired = false

        if (isLaidOut) {
            what.invoke(this, width, height)
        } else {
            viewTreeObserver.addOnPreDrawListener(object : ViewTreeObserver.OnPreDrawListener {
                override fun onPreDraw(): Boolean {
                    if(!isFired) {
                        isFired = true
                        viewTreeObserver.removeOnPreDrawListener(this)
                        what.invoke(this@afterPreDraw, width, height)
                    }
                    return true
                }
            })
        }
    }
}
