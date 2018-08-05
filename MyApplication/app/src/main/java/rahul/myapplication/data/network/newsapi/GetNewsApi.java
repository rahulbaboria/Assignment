package rahul.myapplication.data.network.newsapi;


import android.support.annotation.NonNull;

import rahul.myapplication.config.ApiConstants;
import rahul.myapplication.data.model.NewsResponse;
import rahul.myapplication.data.network.ServiceBuilder;
import rahul.myapplication.data.network.WebServiceInterface;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by rahul on 25/4/18.
 */

public class GetNewsApi {

    private final String API_TAG=GetNewsApi.class.getSimpleName();
    private NetworkCallResponse callback;

    public void getLatestBusinessNews(final NetworkCallResponse mCallback){
        this.callback=mCallback;
        WebServiceInterface webServiceInterface= ServiceBuilder.buildClient(WebServiceInterface.class, ApiConstants.SERVER_URL);
        Call<NewsResponse> getPairCall=webServiceInterface.getNewsData();

        getPairCall.enqueue(new Callback<NewsResponse>() {
            @Override
            public void onResponse(@NonNull Call<NewsResponse> call, @NonNull Response<NewsResponse> response) {
                if(response.isSuccessful()){
                    callback.onSuccess(response.body());
                }else{
                    callback.onServerFailure();
                }
            }
            @Override
            public void onFailure(Call<NewsResponse> call, Throwable t) {
                callback.onNetworkFailure();
            }
        });
    }
}
