package cc.apollai.alarm

import android.app.AlarmManager
import android.app.PendingIntent
import android.app.Service
import android.content.Context
import android.content.Intent
import android.icu.util.Calendar
import android.os.Build
import android.util.Log

class SimpleClock(private val context: Context) {
    private val TAG = "SimpleClock"

    fun setAlarm(hours: Int, minutes: Int){
        Log.d(TAG, "Set alarm is called with $hours: $minutes")

        val calendar = Calendar.getInstance()
        calendar.set(
            calendar.get(Calendar.YEAR),
            calendar.get(Calendar.MONTH),
            calendar.get(Calendar.DAY_OF_MONTH),
            hours,
            minutes,
            0
        )

        val alarmManager = context.getSystemService(Service.ALARM_SERVICE) as AlarmManager

        val intent = Intent(context, AlarmReceiver::class.java).also {
            it.action = AlarmReceiver.INTENT_NAME
            it.putExtra("Hours", hours)
            it.putExtra("Minutes", minutes)
        }

        val pendingIntent = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            PendingIntent.getBroadcast(context, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_MUTABLE)

        } else {
            PendingIntent.getBroadcast(context, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT)
        }

        alarmManager.set(AlarmManager.RTC, calendar.timeInMillis, pendingIntent)
    }
}