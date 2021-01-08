package com.milkyway.milkyway.util

import android.annotation.SuppressLint
import android.content.Context
import android.provider.Settings

object UUID {

    @SuppressLint("HardwareIds")
    fun uuid(context : Context) = Settings.Secure.getString(context.contentResolver, Settings.Secure.ANDROID_ID)
}