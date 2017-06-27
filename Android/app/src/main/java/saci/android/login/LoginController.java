package saci.android.login;

import android.content.Context;
import android.content.SharedPreferences;

import org.json.JSONObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;
import saci.android.CustomPreferences;
import saci.android.R;
import saci.android.dtos.Oauth2Response;
import saci.android.dtos.UserDto;
import saci.android.network.RestClient;
import saci.android.network.UserApi;

/**
 * Created by Corina on 5/25/2017.
 */
public class LoginController {

    private final Context mContext;
    private final UserDto userDto;

    public LoginController(Context context, UserDto userDto) {
        this.mContext = context;
        this.userDto = userDto;
    }

    public Oauth2Response verifyCredentials(Callback<Oauth2Response> callback) {
        UserApi userApi = RestClient.getClient().create(UserApi.class);
        final Oauth2Response oauth2Response = new Oauth2Response();
        userApi.login("password", userDto.getUsername(), userDto.getPassword()).enqueue(callback);

        userApi.getUser(userDto).enqueue(new Callback<UserDto>() {
            @Override
            public void onResponse(Call<UserDto> call, Response<UserDto> response) {
                SharedPreferences preferences = mContext.getApplicationContext().getSharedPreferences("saci.android", Context.MODE_PRIVATE);

                preferences.edit().putString(CustomPreferences.USER_ID, response.body().getId()).apply();
            }

            @Override
            public void onFailure(Call<UserDto> call, Throwable t) {

            }
        });

        return oauth2Response;
    }
}
