package cc.apollai.alarm

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.DialogFragment
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {
    private lateinit var fab : FloatingActionButton
    private lateinit var popupTimePicker: DialogFragment
    private var selectedHours : Int = 0
    private var selectedMins : Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        fab = findViewById(R.id.fabAddAlarm)
        popupTimePicker = AddAlarm()
        val fragmentManager = supportFragmentManager

        fab.setOnClickListener {
            popupTimePicker.show(fragmentManager, "Choose time.")
        }
    }

    @SuppressLint("SetTextI18n")
    fun setTime(selectedHours: Int, selectedMins: Int){
        this.selectedHours = selectedHours
        this.selectedMins = selectedMins
        findViewById<TextView>(R.id.tvTime).text = "${this.selectedHours}:${this.selectedMins}"

        val simpleClock = SimpleClock(applicationContext)
        simpleClock.setAlarm(this.selectedHours, this.selectedMins)

        Snackbar.make(fab,"Alarm is set to ${this.selectedHours}:${this.selectedMins}", Snackbar.LENGTH_SHORT )
            .setAction("OK"){}
            .setAnchorView(R.id.fabAddAlarm)
            .setBackgroundTint(ContextCompat.getColor(this, R.color.background_card))
            .setTextColor(ContextCompat.getColor(this, R.color.white))
            .setActionTextColor(ContextCompat.getColor(this, R.color.teal_200))
            .show()
    }
}