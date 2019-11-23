package id.co.wicata.app.remote;

import java.util.List;

import id.co.wicata.app.model.Response;
import id.co.wicata.app.model.User;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface ApiService {
    @FormUrlEncoded
    @POST("user/confirmation")
    Call<Response> confirmation(
            @Field("email") String email);

    @FormUrlEncoded
    @POST("user/verification")
    Call<Response> verification(
            @Field("email") String email,
            @Field("code") String code);

    @FormUrlEncoded
    @POST("user/login")
    Call<Response> register(
            @Field("email") String email,
            @Field("code") String password);
}
