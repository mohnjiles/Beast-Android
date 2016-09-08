package xyz.jtmiles.beastforgw2.fragments

import android.app.AlarmManager
import android.app.Dialog
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.support.v7.app.AlertDialog
import android.widget.EditText
import android.widget.RadioButton
import android.widget.TextView
import android.widget.Toast
import org.jetbrains.anko.onFocusChange
import org.joda.time.DateTime
import org.joda.time.DateTimeZone
import org.joda.time.Period
import org.joda.time.format.DateTimeFormat
import org.joda.time.format.PeriodFormatterBuilder
import xyz.jtmiles.beastforgw2.BossReceiver
import xyz.jtmiles.beastforgw2.R
import xyz.jtmiles.beastforgw2.models.WorldBoss

class TimerDialogFragment  : DialogFragment() {

    var tvBossName: TextView? = null
    var etCustomTime: EditText? = null

    var rbCustom: RadioButton? = null
    var rb15min: RadioButton? = null
    var rb10min: RadioButton? = null
    var rb5min: RadioButton? = null
    var rb1min: RadioButton? = null


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val boss = arguments.getSerializable("boss") as WorldBoss
        tvBossName?.text = activity.resources.getString(R.string.timer_help).replace("{{boss_name}}", boss.eventName!!)
        etCustomTime?.onFocusChange { view, isFocused ->
            if (isFocused) {
                rbCustom?.isChecked = true
            }
        }
    }



    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val inflater = activity.layoutInflater

        val view = inflater.inflate(R.layout.time_picker_layout, null)
        val builder = AlertDialog.Builder(activity)

        tvBossName = view.findViewById(R.id.tvBossName) as TextView?
        etCustomTime = view.findViewById(R.id.etCustomTime) as EditText?

        rbCustom = view.findViewById(R.id.rbCustom) as RadioButton?
        rb15min = view.findViewById(R.id.rb15min) as RadioButton?
        rb10min = view.findViewById(R.id.rb10min) as RadioButton?
        rb5min = view.findViewById(R.id.rb5min) as RadioButton?
        rb1min = view.findViewById(R.id.rb1min) as RadioButton?



        builder.setView(view)
        builder.setPositiveButton("Set Reminder", { dialogInterface, i ->
            val boss = arguments.getSerializable("boss") as WorldBoss

            var delay = 0

            if (rb15min!!.isChecked) {
                delay = 15
            } else if (rb10min!!.isChecked) {
                delay = 10
            } else if (rb5min!!.isChecked) {
                delay = 5
            } else if (rb1min!!.isChecked) {
                delay = 1
            } else if (rbCustom!!.isChecked) {
                delay = etCustomTime!!.text.toString().toInt()
            }


            val timeAfterDelay = boss.start!!.withZone(DateTimeZone.getDefault()).minusMinutes(delay)

            if (timeAfterDelay <= DateTime.now(DateTimeZone.getDefault())) {

                val start = boss.start
                val end = DateTime()
                val p = Period(end, start)
                val periodFormatter = PeriodFormatterBuilder()
                        .appendHours()
                        .appendSuffix(" hr").appendSeparator(" and ")
                        .appendMinutes().appendSuffix(" minutes").toFormatter()

                val timeString = p.toString(periodFormatter)

                Toast.makeText(activity, "Sorry, you cannot set a timer for $delay minutes when the boss starts in $timeString.", Toast.LENGTH_SHORT).show()
            } else {

                val alarmMgr = activity.getSystemService(Context.ALARM_SERVICE) as AlarmManager
                val intent = Intent(activity, BossReceiver::class.java)
                intent.action = "xyz.jtmiles.beastforgw2.BOSS_ALARM"
                intent.putExtra("boss", boss)
                val alarmIntent = PendingIntent.getBroadcast(activity, (System.currentTimeMillis().toInt()), intent, Intent.FILL_IN_DATA)

                alarmMgr.set(AlarmManager.RTC_WAKEUP, timeAfterDelay.millis, alarmIntent)

                val formatter = DateTimeFormat.forPattern("hh:mm a")

                Toast.makeText(activity, "Timer for ${boss.eventName} set for ${timeAfterDelay.toString(formatter)}", Toast.LENGTH_SHORT).show()
            }

        })
        builder.setNegativeButton("Cancel", null)
        return builder.create()
    }

    companion object {

        fun newInstance(boss: WorldBoss): TimerDialogFragment {
            val fragment = TimerDialogFragment()
            val bundle = Bundle()
            bundle.putSerializable("boss", boss)
            fragment.arguments = bundle
            return fragment
        }
    }
}