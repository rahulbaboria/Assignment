package rahul.myapplication.data.network;

import com.google.gson.JsonObject;


import rahul.myapplication.config.ApiConstants;
import rahul.myapplication.data.model.NewsResponse;
import retrofit2.Call;
import retrofit2.http.GET;


/**
 * Created by rahul on 4/8/18.
 */

public interface WebServiceInterface {

    @GET(ApiConstants.API_URL)
    Call<NewsResponse> getNewsData();

}
