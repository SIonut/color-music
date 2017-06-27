package saci.android.network;

import java.io.IOException;

import okhttp3.Authenticator;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.Route;

/**
 * @author Ion, 6/27/2017.
 */

public class TokenAuthenticator implements Authenticator {

    @Override
    public Request authenticate(Route route, Response response) throws IOException {

//        return response.request().newBuilder()
//                .header("Authorization", AccessToken)
//                .build();
        return null;
    }

}
