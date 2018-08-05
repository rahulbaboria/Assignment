package rahul.myapplication.ui.news;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import rahul.myapplication.R;
import rahul.myapplication.data.database.OnDbResponseListener;
import rahul.myapplication.data.database.news.ReadNewsAsync;
import rahul.myapplication.data.model.News;
import rahul.myapplication.ui.base.BaseFragment;

/**
 * A simple {@link Fragment} subclass.
 */
public class NewsFragment extends BaseFragment implements NewsMvpView, OnDbResponseListener {
    @BindView(R.id.rv_news)
    RecyclerView rvNews;
    @BindView(R.id.bt_retry)
    Button btRetry;
    Unbinder unbinder;


    private AppCompatActivity activity;
    private NewsMvpPresenter<NewsMvpView> presenter;
    private List<News> newsList;

    public NewsFragment() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        activity = (AppCompatActivity) context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.news_fragment, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    protected void setUp(View view) {
        presenter = new NewsPresenter<>();
        presenter.onAttach(this);
        new ReadNewsAsync(activity, this, isNetworkConnected()).execute();

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void onDbResponse(List<News> newsList) {
        this.newsList = newsList;
        setUpNewsAdapter();
    }

    @Override
    public void onFailure() {
        btRetry.setVisibility(View.VISIBLE);
    }

    private void setUpNewsAdapter() {
        NewsAdapter adapter = new NewsAdapter(newsList, activity);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false);
        rvNews.setNestedScrollingEnabled(false);
        rvNews.setLayoutManager(layoutManager);
        rvNews.setAdapter(adapter);
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(rvNews.getContext(),
                LinearLayoutManager.VERTICAL);
        rvNews.addItemDecoration(dividerItemDecoration);
    }


    @OnClick(R.id.bt_retry)
    public void onViewClicked() {
        if (!isNetworkConnected()){
            showToast(R.string.no_internet, Toast.LENGTH_LONG);
        }else {
            btRetry.setVisibility(View.GONE);
            new ReadNewsAsync(activity, this, false).execute();
        }
    }
}
