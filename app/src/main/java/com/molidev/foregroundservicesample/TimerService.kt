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

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        Notifications.createChannel(
            context = applicationContext,
            channelName = R.string.app_name,
            channelDescription = R.string.app_name)
        Notifications.createNotification(
            context = applicationContext,
            title = "NotificationTest",
            content = "This a test notification"
        )
        return super.onStartCommand(intent, flags, startId)
    }
}