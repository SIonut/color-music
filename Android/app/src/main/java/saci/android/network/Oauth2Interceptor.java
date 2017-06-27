package saci.android.network;

import android.content.SharedPreferences;
import android.support.annotation.NonNull;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;
import saci.android.ColorMusicApplication;
import saci.android.CustomPreferences;

/**
 * @author Ion, 6/27/2017.
 */

class Oauth2Interceptor implements Interceptor {

    @Override
    public Response intercept(@NonNull Chain chain) throws IOException {
        SharedPreferences sharedPreferences = ColorMusicApplication.getSharedPreferences();
        Request request = chain.request();
        Request.Builder builder = request.newBuilder().addHeader("Authorization",
                sharedPreferences.getString(CustomPreferences.ACCESS_TOKEN, ""));
        return chain.proceed(builder.build());
    }
}
