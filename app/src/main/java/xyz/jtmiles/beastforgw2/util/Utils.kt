package xyz.jtmiles.beastforgw2.util

import android.content.Context
import android.content.SharedPreferences
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

/**
 * Created by JT on 5/15/2016.
 */
object Utils {

    fun getSharedPrefs(context: Context): SharedPreferences {
        return context.getSharedPreferences("xyz.jtmiles.beastforgw2", Context.MODE_PRIVATE)
    }

    fun getRetrofit(useV2api: Boolean): Retrofit {
        if (useV2api) {
            return Retrofit.Builder().baseUrl(Constants.BASE_URL_V2).addConverterFactory(GsonConverterFactory.create()).build()
        } else {
            return Retrofit.Builder().baseUrl(Constants.BASE_URL_V1).addConverterFactory(GsonConverterFactory.create()).build()
        }
    }

    fun getApiKeyForAuth(context: Context): String {
        return "Bearer " + Utils.getSharedPrefs(context).getString(Constants.PREF_API_KEY, "")!!
    }

    fun getResourceIdByName(context: Context, name: String): Int {
        val resources = context.resources

        val drawableName = name.replace("[^A-Za-z]".toRegex(), "").toLowerCase()

        return resources.getIdentifier(drawableName, "drawable", context.packageName)
    }

    const val ISO8601DATEFORMAT = "yyyy-MM-dd'T'HH:mm:ss'Z'"

    fun getCalendarFromISO(datestring: String): Calendar {


        val calendar = Calendar.getInstance(TimeZone.getDefault(), Locale.getDefault())
        val dateFormat = SimpleDateFormat(ISO8601DATEFORMAT, Locale.getDefault())
        try {
            val date = dateFormat.parse(datestring)
            calendar.time = date
        } catch (e: ParseException) {
            e.printStackTrace()
        }


        return calendar
    }
}
