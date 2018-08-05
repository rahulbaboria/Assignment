package rahul.myapplication.data.database;

import java.util.List;

import rahul.myapplication.data.model.News;

/**
 * Created by rahul on 4/8/18.
 */

public interface OnDbResponseListener {
    void onDbResponse(List<News> currencyPairList);
    void onFailure();
}
