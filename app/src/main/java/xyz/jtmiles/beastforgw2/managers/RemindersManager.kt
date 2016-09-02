package xyz.jtmiles.beastforgw2.managers

import android.content.Context
import android.util.Log
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import xyz.jtmiles.beastforgw2.models.Reminder
import xyz.jtmiles.beastforgw2.serializers.Converters
import java.io.BufferedReader
import java.io.File
import java.io.FileReader
import java.util.*

class RemindersManager(val mContext: Context) {

    fun loadReminders() : ArrayList<Reminder> {
        val fileName = "reminders.json"
        val gson = Converters.registerDateTime(GsonBuilder()).create()
        val sb = StringBuilder()

        try {
            val file = File("${mContext.filesDir.absolutePath}/$fileName")
            val bufferedReader = BufferedReader(FileReader(file))
            var line = bufferedReader.readLine()
            while(line != null) {
                sb.append(line)
                line = bufferedReader.readLine()
            }
            bufferedReader.close()

        } catch (ex: Exception){
            Log.w("StorageManager", "Error loading reminders: ${ex.message}")
        }

        val listType = object: TypeToken<ArrayList<Reminder>>() {}.type

        return gson.fromJson<ArrayList<Reminder>>(sb.toString(), listType) ?: ArrayList<Reminder>()
    }

    fun saveReminders(bankItems: List<Reminder>) {
        val fileName = "reminders.json"
        val gson = Converters.registerDateTime(GsonBuilder()).create()

        val json = gson.toJson(bankItems)

        try {
            val outputStream = mContext.openFileOutput(fileName, Context.MODE_PRIVATE)
            outputStream.write(json.toByteArray())
            outputStream.close()
        } catch (ex: Exception){
            Log.w("StorageManager", "Error creating file: ${ex.message}")
        }
    }
}