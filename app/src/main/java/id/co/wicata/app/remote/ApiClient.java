package id.co.wicata.app.remote;

import id.co.wicata.app.Utils.Constants;
import id.co.wicata.app.model.Response;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient
implements Constants {
    private Retrofit retrofit;
    private ApiService service;

    private Retrofit getRetrofit(){
        retrofit = new Retrofit.Builder()
                .baseUrl(API_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        return retrofit;
    }

    public ApiService getService(){
        service = getRetrofit().create(ApiService.class);
        return service;
    }
}
