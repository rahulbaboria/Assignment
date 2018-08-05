package rahul.myapplication.data.network.newsapi;

import rahul.myapplication.data.model.NewsResponse;


/**
 * Created by rahul on 4/8/18.
 */

public interface NetworkCallResponse {

    void onSuccess(NewsResponse responseBody);

    void onNetworkFailure();

    void onServerFailure();

}
