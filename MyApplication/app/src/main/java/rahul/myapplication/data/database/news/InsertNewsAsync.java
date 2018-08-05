package rahul.myapplication.data.database.news;
import android.content.Context;
import android.os.AsyncTask;
import android.support.v7.app.AlertDialog;
import java.util.List;

import rahul.myapplication.data.database.AppDatabase;
import rahul.myapplication.data.database.OnDbResponseListener;
import rahul.myapplication.data.model.News;
import rahul.myapplication.ui.utils.UiUtils;

/**
 * Created by rahul on 4/4/18.
 */

public class InsertNewsAsync extends AsyncTask<List<News>,Void,Void> {

    private AppDatabase appDatabase;
    private Context context;
    private AlertDialog progressDialog;
    private OnDbResponseListener onDbResponseListener;

    public InsertNewsAsync(Context context, OnDbResponseListener onDbResponseListener){
       this.context=context;
       this.appDatabase=AppDatabase.getInstance(context);
       this.onDbResponseListener= onDbResponseListener;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        progressDialog= new UiUtils().showProgressBar(context);
        progressDialog.show();
        progressDialog.setCancelable(false);
    }

    @SafeVarargs
    @Override
    protected final Void doInBackground(List<News>... newsList) {
        List<News> news=newsList[0];
        appDatabase.newsDao().insertAll(news);
        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
        new ReadNewsAsync(context,onDbResponseListener,false).execute();
        progressDialog.dismiss();
    }
}
