package com.molidev.foregroundservicesample

import android.app.Service
import android.content.Intent
import android.os.Binder
import android.os.IBinder

class TimerService : Service() {

    override fun onBind(p0: Intent?): IBinder? {
        TODO("Not yet implemented")
    }

    inner class TimerBinder : Binder() {
        val service: TimerService
            get() = this@TimerService
    }
}