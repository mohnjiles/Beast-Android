package xyz.jtmiles.beastforgw2

import android.app.NotificationManager
import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.support.v4.app.NotificationCompat
import android.support.v4.app.TaskStackBuilder
import org.joda.time.DateTime
import org.joda.time.Period
import org.joda.time.format.PeriodFormatterBuilder
import xyz.jtmiles.beastforgw2.activities.MainActivity
import xyz.jtmiles.beastforgw2.models.WorldBoss

class BossReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context?, intent: Intent?) {
        val boss = intent!!.getSerializableExtra("boss") as WorldBoss

        val builder = NotificationCompat.Builder(context)
        builder.setSmallIcon(R.drawable.ic_timer_white_24dp)
        builder.setContentTitle("Boss Reminder")
        builder.setAutoCancel(true)


        val start = boss.start
        val end = DateTime()
        val p = Period(end, start)
        val periodFormatter = PeriodFormatterBuilder()
                .appendHours()
                .appendSuffix("h").appendSeparator(" ")
                .appendMinutes().appendSuffix("m").toFormatter()

        val timeString = p.toString(periodFormatter)

        builder.setContentText("${boss.eventName} spawning in $timeString")

        val resultIntent = Intent(context, MainActivity::class.java)
        resultIntent.putExtra("DoBossFragment", true)

        val stackBuilder = TaskStackBuilder.create(context)
        stackBuilder.addParentStack(MainActivity::class.java)
        stackBuilder.addNextIntent(resultIntent)

        val pendingIntent = stackBuilder.getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT)
        builder.setContentIntent(pendingIntent)

        val notificationManager = context!!.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        notificationManager.notify((System.currentTimeMillis().toInt()), builder.build())
    }

}