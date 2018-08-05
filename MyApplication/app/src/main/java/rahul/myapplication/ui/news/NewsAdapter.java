package rahul.myapplication.ui.news;

import android.content.Context;
import android.os.Build;
import android.os.Handler;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;


import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import rahul.myapplication.R;
import rahul.myapplication.data.model.News;
import rahul.myapplication.ui.utils.DateUtils;

/**
 * Created by rahul on 16/8/17.
 */


public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.MyViewHolder> {


    private final List<News> newsList;
    private Context mContext;


    public NewsAdapter(List<News> newsList, final Context mContext) {
        this.mContext = mContext;
        this.newsList = newsList;
        Log.d("NewsAdapter","Sending news "+newsList.size());

    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(mContext).inflate(R.layout.news_item, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
         News news = newsList.get(position);
         holder.tvTitle.setText(news.getTitle());
         holder.tvDes.setText(news.getDescription());
         holder.tvPublishedAt.setText(String.format("Published at %s", DateUtils.getFormattedDate(news.getPublishedAt())));


        Picasso.get().load(news.getUrlToImage())
                  .resize(640,360)
                  .centerCrop()
               // .error(R.drawable.image_not_available)
                .into(holder.ivNewsImage);
    }

    @Override
    public int getItemCount() {
        return newsList.size();
    }


    public static class MyViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.tv_title)
        TextView tvTitle;
        @BindView(R.id.tv_published)
        TextView tvPublishedAt;
        @BindView(R.id.tv_des)
        TextView tvDes;
        @BindView(R.id.iv_news)
        ImageView ivNewsImage;

        public MyViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);

        }
    }
}
