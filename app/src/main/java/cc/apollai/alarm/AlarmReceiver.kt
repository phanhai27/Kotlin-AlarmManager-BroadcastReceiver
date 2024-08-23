package cc.apollai.alarm

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log
import android.widget.Toast

class AlarmReceiver: BroadcastReceiver() {
    private val TAG = "AlarmReceiver"

    companion object {
        const val INTENT_NAME = "cc.apollai.alarm"
    }

    override fun onReceive(context: Context?, intent: Intent?) {
        Log.d(TAG, "Receiver is invoked.")

        if(intent!!.action.equals(INTENT_NAME)){
            val bundle = intent.extras
            Log.d(TAG, "Time is up. ${bundle?.getInt("Hours")}:${bundle?.getInt("Minutes")}")

            Toast.makeText(context, "Time is up. ${bundle?.getInt("Hours")}:${bundle?.getInt("Minutes")}", Toast.LENGTH_LONG).show()
        }
    }
}