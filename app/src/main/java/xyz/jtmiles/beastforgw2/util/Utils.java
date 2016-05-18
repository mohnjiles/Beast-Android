package xyz.jtmiles.beastforgw2.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Resources;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by JT on 5/15/2016.
 */
public class Utils {

    public static SharedPreferences getSharedPrefs(Context context){
        return context.getSharedPreferences("xyz.jtmiles.beastforgw2", Context.MODE_PRIVATE);
    }

    private static Retrofit mRetrofit;
    public static Retrofit getRetrofit(boolean useV2api) {

        if (mRetrofit == null) {
            if (useV2api) {
                mRetrofit = new Retrofit.Builder()
                        .baseUrl(Constants.BASE_URL_V2)
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();
            } else {
                mRetrofit = new Retrofit.Builder()
                        .baseUrl(Constants.BASE_URL_V1)
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();
            }
        }
        return mRetrofit;
    }

    public static String getApiKey(Context context) {
        return "Bearer " + Utils.getSharedPrefs(context).getString(Constants.API_KEY_PREF, "");
    }

    public static int getResourceIdByName(Context context, String name) {
        Resources resources = context.getResources();

        String drawableName = name.replaceAll("[^A-Za-z]", "").toLowerCase();

        return resources.getIdentifier(drawableName, "drawable", context.getPackageName());
    }

    final static String ISO8601DATEFORMAT = "yyyy-MM-dd'T'HH:mm:ss'Z'";

    public static Calendar getCalendarFromISO(String datestring) {


        Calendar calendar = Calendar.getInstance(TimeZone.getDefault(), Locale.getDefault()) ;
        SimpleDateFormat dateFormat = new SimpleDateFormat(ISO8601DATEFORMAT, Locale.getDefault());
        try {
            Date date = dateFormat.parse(datestring);
            calendar.setTime(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }


        return calendar;
    }
}
