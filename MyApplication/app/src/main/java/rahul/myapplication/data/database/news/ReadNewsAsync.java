package rahul.myapplication.data.database.news;

import android.content.Context;
import android.os.AsyncTask;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import rahul.myapplication.R;
import rahul.myapplication.data.database.AppDatabase;
import rahul.myapplication.data.database.OnDbResponseListener;
import rahul.myapplication.data.model.News;
import rahul.myapplication.data.model.NewsResponse;
import rahul.myapplication.data.network.newsapi.GetNewsApi;
import rahul.myapplication.data.network.newsapi.NetworkCallResponse;

/**
 * Created by rahul on 4/4/18.
 */

public class ReadNewsAsync extends AsyncTask<Void,Void,List<News>> {
    private AppDatabase appDatabase;
    private OnDbResponseListener onDbResponseListener;
    public Context context;
    private boolean isInternetConnected;


    public ReadNewsAsync(Context context, OnDbResponseListener onDbResponseListener,boolean isInternetConnected){
        this.appDatabase=AppDatabase.getInstance(context);
        this.context = context;
        this.onDbResponseListener=onDbResponseListener;
        this.isInternetConnected = isInternetConnected;
    }

    @Override
    protected List<News> doInBackground(Void... voids) {
        List<News> newsList = appDatabase.newsDao().getAllItems();
        if ((newsList==null||newsList.isEmpty())||isInternetConnected){
            new GetNewsApi().getLatestBusinessNews(new NetworkCallResponse() {
                @Override
                public void onSuccess(NewsResponse responseBody) {
                   handleAndSaveNews(responseBody);
                }

                @Override
                public void onNetworkFailure() {
                    onDbResponseListener.onFailure();
                }

                @Override
                public void onServerFailure() {
                    onDbResponseListener.onFailure();
                }
            });
        }
        Log.d("ReadNews","Sending news "+newsList);

        return newsList;
    }

    private void handleAndSaveNews(NewsResponse responseBody) {
        if (responseBody.getArticles()!=null&&!responseBody.getArticles().isEmpty()){
            List<News> newsList = new ArrayList<>();
            for (NewsResponse.Article article:responseBody.getArticles()){
                newsList.add(new News(article.getTitle(),article.getDescription(),article.getAuthor(),article.getUrlToImage(),article.getPublishedAt()));
            }
            Log.d("InsertNewsAsync","Sending news "+newsList.size());

            new InsertNewsAsync(context,onDbResponseListener).execute(newsList);
        }

    }

    @Override
    protected void onPostExecute(List<News> news) {
        super.onPostExecute(news);
        onDbResponseListener.onDbResponse(news);
    }
}
