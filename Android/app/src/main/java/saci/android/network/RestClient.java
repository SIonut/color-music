package saci.android.network;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

/**
 * @author Ion, 6/27/2017.
 */

public class RestClient {

    public static final String BASE_URL = "http://172.24.30.49:8080/";

    public static Retrofit getClient() {
        OkHttpClient.Builder httpClientBuilder = new OkHttpClient.Builder();
        httpClientBuilder.authenticator(new TokenAuthenticator());
        httpClientBuilder.addInterceptor(new Oauth2Interceptor());
        return new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(httpClientBuilder.build())
                .addConverterFactory(JacksonConverterFactory.create())
                .build();
    }
}
