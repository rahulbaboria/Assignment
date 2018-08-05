package rahul.myapplication.ui.home;

import rahul.myapplication.R;
import rahul.myapplication.data.model.NewsResponse;
import rahul.myapplication.data.network.newsapi.NetworkCallResponse;
import rahul.myapplication.ui.base.BasePresenter;


public class HomePresenter<V extends HomeMvpView> extends BasePresenter<V> implements HomeMvpPresenter<V>,NetworkCallResponse {
    private String temporaryEmail;


    @Override
    public void onSuccess(NewsResponse responseBody) {

    }

    @Override
    public void onNetworkFailure() {
        getMvpView().hideProgressBar();
        getMvpView().showToast(R.string.invalid_response,0);
    }

    @Override
    public void onServerFailure() {
        getMvpView().hideProgressBar();
        getMvpView().showToast(R.string.invalid_response,0);
    }
}
