package saci.android.login;

import android.content.Context;
import android.content.SharedPreferences;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import saci.android.CustomPreferences;
import saci.android.dtos.Oauth2Response;
import saci.android.dtos.UserDto;
import saci.android.network.RestClient;
import saci.android.network.UserApi;

/**
 * Created by Corina on 5/25/2017.
 */
public class LoginController {

    private final UserApi userApi;
    private final Context mContext;
    private final UserDto userDto;

    public LoginController(Context context, UserDto userDto) {
        this.mContext = context;
        this.userDto = userDto;
        userApi = RestClient.getClient().create(UserApi.class);
    }

    public Oauth2Response verifyCredentials(Callback<Oauth2Response> callback) {
        final Oauth2Response oauth2Response = new Oauth2Response();
        userApi.login("password", userDto.getUsername(), userDto.getPassword()).enqueue(callback);
        return oauth2Response;
    }

    public void fetchUser() {
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
    }
}
