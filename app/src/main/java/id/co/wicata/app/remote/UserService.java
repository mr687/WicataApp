package id.co.wicata.app.remote;

import java.util.List;

import id.co.wicata.app.model.User;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface UserService {
    @GET("user/")
    Call<User> getUsers();

    @POST("create/")
    Call<User> createUser(@Body User user);

    @PUT("update/{id}/{token}")
    Call<User> updateUser(@Path("id") int id, @Path("token") String token, @Body User user);
}
