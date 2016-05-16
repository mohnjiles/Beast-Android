package xyz.jtmiles.beastforgw2.util;

import android.content.Context;
import android.content.SharedPreferences;

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
    public static Retrofit getRetrofit() {

        if (mRetrofit == null) {
            mRetrofit = new Retrofit.Builder()
                    .baseUrl(Constants.BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return mRetrofit;
    }

    public static String getApiKey(Context context) {
        return "Bearer " + Utils.getSharedPrefs(context).getString(Constants.API_KEY_PREF, "");
    }
}
