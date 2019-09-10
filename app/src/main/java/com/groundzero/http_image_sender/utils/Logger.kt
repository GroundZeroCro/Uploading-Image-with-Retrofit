package com.groundzero.http_image_sender.utils

import android.util.Log

object Logger {
    fun up(message: String) {
        Log.d(TAG, message)
    }

    private const val TAG = "mogger"
}