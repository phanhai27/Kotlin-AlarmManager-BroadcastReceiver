package cc.apollai.alarm

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TimePicker
import androidx.fragment.app.DialogFragment

class AddAlarm: DialogFragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val timePickerView = inflater.inflate(R.layout.add_alarm, container, false)
        val timepicker = timePickerView.findViewById<TimePicker>(R.id.timePicker)
        val doneButton = timePickerView.findViewById<Button>(R.id.btnDone)

        val mainActivity = activity as MainActivity

        doneButton.setOnClickListener{
            val hours = timepicker.hour
            val minutes = timepicker.minute
            mainActivity.setTime(hours, minutes)
            dismiss()
        }

        return timePickerView
    }
}