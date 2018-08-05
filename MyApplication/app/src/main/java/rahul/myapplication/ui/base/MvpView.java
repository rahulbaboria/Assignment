package rahul.myapplication.ui.base;

import android.support.annotation.StringRes;
import android.support.v7.widget.Toolbar;

public interface MvpView {

    void showProgressBar();

    void hideProgressBar();

    void showToast(String message, int length);

    void showToast(@StringRes int resId, int length);

    boolean isNetworkConnected();

    void setUpToolbar(Toolbar toolbar);

}
