package com.molidev.foregroundservicesample

import android.app.Service
import android.content.Intent
import android.os.Binder
import android.os.IBinder

class TimerService : Service() {

    private val binder: Binder = TimerBinder()

    override fun onBind(p0: Intent?): IBinder = binder

    inner class TimerBinder : Binder() {
        val service: TimerService
            get() = this@TimerService
    }
}