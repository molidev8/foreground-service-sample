package com.molidev.foregroundservicesample

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.os.Bundle
import android.os.IBinder
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.molidev.foregroundservicesample.ui.theme.ForegroundServiceSampleTheme

class MainActivity : ComponentActivity() {

    private lateinit var timerService: TimerService
    private var isTimerServiceBound: Boolean = false
    private val timerServiceConnection = object : ServiceConnection {

        override fun onServiceConnected(name: ComponentName?, binder: IBinder?) {
            timerService = (binder as TimerService.TimerBinder).service
            isTimerServiceBound = true
        }

        override fun onServiceDisconnected(p0: ComponentName?) {
            isTimerServiceBound = false
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        startService(Intent(this, TimerService::class.java))
        bindService(
            Intent(this, TimerService::class.java),
            timerServiceConnection,
            Context.BIND_AUTO_CREATE
        )

        setContent {
            ForegroundServiceSampleTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    Greeting("Android")
                }
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        if (isTimerServiceBound) {
            unbindService(timerServiceConnection)
        }
    }
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ForegroundServiceSampleTheme {
        Greeting("Android")
    }
}