package saci.android.register;

import android.content.Context;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;
import saci.android.R;
import saci.android.dtos.UserDto;
import saci.android.network.RestClient;
import saci.android.network.UserApi;

/**
 * Created by Corina on 5/25/2017.
 */
public class RegisterController {

    private final Context mContext;
    private UserDto userDto;

    public RegisterController(Context context, UserDto userDto) {
        this.mContext = context;
        this.userDto = userDto;
    }

    public void createAccount() {
        UserApi userApi = RestClient.getClient().create(UserApi.class);

        userApi.register(userDto).enqueue(new Callback<UserDto>() {
            @Override
            public void onResponse(Call<UserDto> call, Response<UserDto> response) {

            }

            @Override
            public void onFailure(Call<UserDto> call, Throwable t) {

            }
        });

    }
}
