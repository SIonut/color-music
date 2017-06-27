package saci.android.network;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import saci.android.dtos.SongDto;

/**
 * Created by corina on 26.06.2017.
 */
public interface SongsApi {

    @GET("api/songs/{color}")
    Call<List<SongDto>> getByColor(@Path("color") String color);

    @GET("api/songs/id/{id}")
    Call<SongDto> getById(@Path("id") String id);

    @GET("api/users/{userId}/likes/{songId}")
    Call<Boolean> isLiked(@Path("userId") String userId, @Path("songId") String songId);

    @POST("api/users/{userId}/likes/add/{songId}")
    Call<Boolean> addToLikes(@Path("userId") String userId, @Path("songId") String songId);

    @POST("api/users/{userId}/likes/remove/{songId}")
    Call<Boolean> deleteFromLikes(@Path("userId") String userId, @Path("songId") String songId);
}
