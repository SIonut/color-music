package saci.android.network;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import saci.android.dtos.PlaylistDto;

/**
 * Created by corina on 26.06.2017.
 */
public interface PlaylistApi {

    @GET("api/users/{userId}/likes")
    Call<PlaylistDto> getLikes(@Path("userId") String userId);

    @GET("api/playlists/{userId}/playlists")
    Call<List<PlaylistDto>> getPlaylists(@Path("userId") String userId);

    @PUT("api/playlists")
    Call<PlaylistDto> updatePlaylist(@Body PlaylistDto playlistDto);

    @POST("api/playlists")
    Call<PlaylistDto> createPlaylist(@Body PlaylistDto playlistDto);

    @GET("api/playlists/{playlistId}")
    Call<PlaylistDto> getPlaylist(@Path("playlistId") String playlistId);

    @GET("api/playlists/top")
    Call<List<PlaylistDto>> getTopPlaylists();
}
