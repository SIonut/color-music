package saci.android.network;

import android.content.SharedPreferences;
import android.support.annotation.NonNull;

import java.io.IOException;

import okhttp3.HttpUrl;
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
        if (request.url().toString().contains("api/users/register")) {
            return chain.proceed(request.newBuilder().addHeader("Content-Type", "application/json").build());
        }
        HttpUrl url = request.url().newBuilder().addQueryParameter("access_token",
                sharedPreferences.getString(CustomPreferences.ACCESS_TOKEN, "")).build();
        Request.Builder builder = request.newBuilder().url(url);
        return chain.proceed(builder.build());
    }
}
