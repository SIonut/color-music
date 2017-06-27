package saci.android.network;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import saci.android.dtos.Oauth2Response;
import saci.android.dtos.UserDto;

/**
 * Created by corina on 26.06.2017.
 */
public interface UserApi {

    @POST("api/users/register")
    Call<UserDto> register(@Body UserDto userDto);

    @POST("api/users")
    Call<UserDto> getUser(@Body UserDto userDto);

    @FormUrlEncoded
    @Headers({
            "Accept: */*",
            "Authorization: Basic YW5kcm9pZDpmMDRhYWFkYjRmNjczNjg0YzAwM2ZlYzc3ZDU4ODk2Nzc2MzY1NmMw"
    })
    @POST("oauth/token")
    Call<Oauth2Response> login(@Field("grant_type") String grantType,
                               @Field("username") String username,
                               @Field("password") String password);

}
